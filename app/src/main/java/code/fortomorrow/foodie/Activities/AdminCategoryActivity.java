package code.fortomorrow.foodie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import code.fortomorrow.foodie.AdminAddNewProductActivity;
import code.fortomorrow.foodie.R;

public class AdminCategoryActivity extends AppCompatActivity {
    private ImageView tShirts;
    private ImageView glasses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);
        tShirts = (ImageView) findViewById(R.id.t_shirts);
        glasses = (ImageView) findViewById(R.id.glasses);

         tShirts.setOnClickListener(new android.view.View.OnClickListener() {
             @Override
             public void onClick(android.view.View v) {
                 Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                 intent.putExtra("category", "tShirts");
                 startActivity(intent);
             }
         });

//        glasses.setOnClickListener(new android.view.View.OnClickListener() {
//            @Override
//            public void onClick(android.view.View v)
//            {
//                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddFoodActivity.class);
//                intent.putExtra("category", "Glasses");
//                startActivity(intent);
//            }
//        });


        Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();
    }
}