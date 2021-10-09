package code.fortomorrow.foodie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import code.fortomorrow.foodie.Adapters.FoodListAdapter;
import code.fortomorrow.foodie.R;
import code.fortomorrow.foodie.SharedPref;

public class ConfirmOrderActivity extends AppCompatActivity {
    private EditText shippment_name,shippment_phone_number,shippment_address,dressType;
    private String productname="";
    private String productprice="";
    private Button button4;
    private Button confirm_final_order_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirm_order);

        button4 = findViewById(R.id.btn4);
        confirm_final_order_btn = findViewById(R.id.confirm_btn);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent co = new Intent(ConfirmOrderActivity.this, FoodListAdapter.class);
                startActivity(co);
            }
        });

        confirm_final_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmOrderActivity.this,PaymentActivity.class));
                finish();
            }
        });


        init();
        SharedPref.init(ConfirmOrderActivity.this);
        shippment_name.setText(SharedPref.read("name",""));
        shippment_phone_number.setText(SharedPref.read("phone",""));
        shippment_address.setText(SharedPref.read("address",""));
        productname = getIntent().getStringExtra("name");
        productprice = getIntent().getStringExtra("price");

    }

    private void check() {
        if(TextUtils.isEmpty(shippment_name.getText().toString())){
            shippment_name.setError("Please provide your full name");
        }
        else if(TextUtils.isEmpty(shippment_phone_number.getText().toString())){
            shippment_phone_number.setError("Please provide your phone number");
        }
        else if(TextUtils.isEmpty(shippment_address.getText().toString())){
            shippment_address.setError("Please provide your address");
        }
        else if(TextUtils.isEmpty(dressType.getText().toString())){
            dressType.setError("Please provide your desired Table Number");
        }
        else {
            confirmOrder();
        }
    }

    private void confirmOrder() {
        final String saveCurrentDate, saveCurrentTime;
        Calendar callForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(callForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(callForDate.getTime());

        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child(SharedPref.read("phone",""))
                .child(String.valueOf(System.currentTimeMillis()))
                ;

        HashMap<String,Object> ordersMap = new HashMap<>();
        ordersMap.put("totalAmount",productprice);
        ordersMap.put("name",shippment_name.getText().toString());
        ordersMap.put("phone",shippment_phone_number.getText().toString());
        ordersMap.put("adress",shippment_address.getText().toString());
        ordersMap.put("Table_No",dressType.getText().toString());
        ordersMap.put("date",saveCurrentDate);
        ordersMap.put("time",saveCurrentTime);
        ordersMap.put("state","Booked Order");
        ordersMap.put("tailorname",productname);
        ordersMap.put("tailorname",productprice);


        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference()
                            .child("RestaurantWise Orders")
                            .child(SharedPref.read("phone",""))
                            .updateChildren(ordersMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ConfirmOrderActivity.this,"Your final Order has been placed successfully",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(ConfirmOrderActivity.this, ConfirmOrderActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void init() {
        shippment_name = findViewById(R.id.shippment_name);
        shippment_phone_number = findViewById(R.id.shippment_phone_number);
        shippment_address = findViewById(R.id.shippment_address);
        dressType = findViewById(R.id.shipment_city);
    }
}