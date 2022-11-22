package com.hkmus413f.groupproj_moviebuyer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    Button b1;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        db = new DatabaseHelper(this);
        final String key_username = getIntent().getStringExtra("username");   // Pass from LoginActivity and HomePageActivity

        if (key_username != null){
            Cursor res = db.getInfo(key_username);
            if (res.getCount() == 0){
                Toast.makeText(UserInfoActivity.this, "NO INFO", Toast.LENGTH_SHORT).show();
            } else {
                TextView userID = findViewById(R.id.tv_userinfo_id);
                TextView userName = findViewById(R.id.tv_userinfo_username);
                TextView userPW = findViewById(R.id.tv_userinfo_pw);
                TextView userAge = findViewById(R.id.tv_userinfo_age);
                TextView userCredit = findViewById(R.id.tv_userinfo_credit);

                while (res.moveToNext()){
                    userID.setText(res.getString(0));
                    userName.setText(res.getString(1));
                    userPW.setText(res.getString(2));
                    userAge.setText(res.getString(3));
                    userCredit.setText(res.getString(4));
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}