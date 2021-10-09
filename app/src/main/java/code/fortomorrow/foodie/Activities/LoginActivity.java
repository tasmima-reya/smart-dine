package code.fortomorrow.foodie.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Prevalent.Prevalent;
import code.fortomorrow.foodie.Fragments.HomeFragment;
import code.fortomorrow.foodie.MainActivity;
import code.fortomorrow.foodie.Model.Users;
import code.fortomorrow.foodie.R;
import code.fortomorrow.foodie.SharedPref;


public class LoginActivity extends AppCompatActivity {
    private TextView login_btn,sign_up;
    private EditText login_phone_number_input, login_password_input;
    private ProgressDialog loadingBar1,loadingBar2;
    private String parentDbName ="Users";
    private TextView AdminLink, NotAdminLink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);

        init();
        SharedPref.init(this);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorprimary));
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                login_btn.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                login_btn.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";
            }
        });
    }


    private void loginUser() {
        String phone = login_phone_number_input.getText().toString();
        String password = login_password_input.getText().toString();

        if(TextUtils.isEmpty(phone)){
            login_phone_number_input.setError("Phone Field can't be Blank");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            login_password_input.setError("Password Field can't be Blank");
            return;
        }
        else {
            loadingBar1.setTitle("Login Account");
            loadingBar1.setMessage("Please Wait, While we are checking the credentials");
            loadingBar1.setCanceledOnTouchOutside(false);
            loadingBar1.show();
            AllowAccssAccount(phone,password);
        }
    }


    private <databaseError> void AllowAccssAccount(String phone, String password) {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDbName).child(phone).exists()){
                     Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            if (parentDbName.equals("Admins"))
                            {
                                Toast.makeText(LoginActivity.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar1.dismiss();

                                Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                startActivity(intent);
                            }
                            else if (parentDbName.equals("Users"))
                            {
                                Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar1.dismiss();
                    if(usersData.getPhone().equals(phone)){
                        if(usersData.getPassword().equals(password)){
                            if (parentDbName.equals("Users")) {
                                loadingBar1.dismiss();
                                SharedPref.write("phone",phone);
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                        else {
                            loadingBar1.dismiss();
                            Toast.makeText(LoginActivity.this,"password is not Correct",Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this,"Account with this "+ phone+" number do not exists ",Toast.LENGTH_SHORT).show();
                    loadingBar1.dismiss();

                                Intent intent = new Intent(LoginActivity.this, HomeFragment.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            loadingBar1.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Account with this " + phone + " number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar1.dismiss();
                }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private void init() {
        login_phone_number_input = findViewById(R.id.phone_number);
        login_password_input = findViewById(R.id.passwordId);
        sign_up = findViewById(R.id.reset);
        login_btn = findViewById(R.id.signInBtnId);
        loadingBar1 = new ProgressDialog(this);
    }
}

