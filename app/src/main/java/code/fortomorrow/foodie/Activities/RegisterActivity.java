package code.fortomorrow.foodie.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import code.fortomorrow.foodie.R;
import code.fortomorrow.foodie.SharedPref;

public class RegisterActivity extends AppCompatActivity {
    private EditText register_phone_number_input, register_username_input, register_address_input, register_password_input;
    private TextView register_btn;
    private TextView login_back;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SharedPref.init(this);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorprimary));
            init();
            login_back.setOnClickListener(view ->
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class)));

            register_btn.setOnClickListener(v ->
                    createAccount());

        }
    }

    private void createAccount() {
        String name = register_username_input.getText().toString();
        String phone = register_phone_number_input.getText().toString();
        String password = register_password_input.getText().toString();
        String address = register_address_input.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            register_phone_number_input.setError("Phone Field can't be Blank");
            return;
        } else if (TextUtils.isEmpty(name)) {
            register_username_input.setError("Name Field can't be Blank");
            return;
        } else if (TextUtils.isEmpty(password)) {
            register_password_input.setError("Password Field can't be Blank");
            return;
        } else if (TextUtils.isEmpty(address)) {
            register_address_input.setError("Address Field can't be Blank");
            return;
        } else {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please Wait, While we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validatephoneNumber(name, phone, password, address);
        }
    }

    private void validatephoneNumber(final String name, final String phone, final String password, String address) {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("phone", phone);
                    userDataMap.put("password", password);
                    userDataMap.put("name", name);
                    userDataMap.put("address", address);
                    SharedPref.write("phone",phone);
                    SharedPref.write("password",password);
                    SharedPref.write("name",name);
                    SharedPref.write("address",address);

                    Rootref.child("Users").child(phone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Congratulations , Your account is created", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "NetWork error! Please, try again", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                } else {
                    Toast.makeText(RegisterActivity.this, "This " + phone + "Already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again using another Phone Number", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void init() {
        register_phone_number_input = findViewById(R.id.sign_up_phone);
        register_username_input = findViewById(R.id.sign_up_username);
        register_address_input = findViewById(R.id.sign_up_adress);
        register_password_input = findViewById(R.id.sign_up_password);
        register_btn = findViewById(R.id.register_btn);
        login_back = findViewById(R.id.login_back);
        loadingBar = new ProgressDialog(this);
    }
}