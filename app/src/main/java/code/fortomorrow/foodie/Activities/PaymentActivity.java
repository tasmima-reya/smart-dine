package code.fortomorrow.foodie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import code.fortomorrow.foodie.R;

public class PaymentActivity extends AppCompatActivity {


    Button con, dip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        con = findViewById(R.id.cashon);
        dip = findViewById(R.id.digital);


        dip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Dip = new Intent(PaymentActivity.this, DigitalPaymentActivity.class);
                startActivity(Dip);
            }
        });
    }
}