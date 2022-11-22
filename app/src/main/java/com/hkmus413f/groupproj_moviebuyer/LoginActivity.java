package com.hkmus413f.groupproj_moviebuyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        e1 = (EditText)findViewById(R.id.et_login_email);
        e2 = (EditText)findViewById(R.id.et_login_password);
        b1 = (Button) findViewById(R.id.btn_submit_login);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean Chkemailpass = db.emailpassword(username,password);
                if (Chkemailpass == true) {
                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomePageActivity.class);      //if login success then jump to Main page
                    i.putExtra("username",username);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void btn_sign_up(View v){
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    public void btn_guest(View v){
        Intent i = new Intent(this, GuestActivity.class);
        startActivity(i);
    }

}