package com.hkmus413f.groupproj_moviebuyer;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    Button b1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.username);
        e2 = (EditText)findViewById(R.id.age);
        e3 = (EditText)findViewById(R.id.pass);
        e4 = (EditText)findViewById(R.id.cpass);
        b1 = (Button) findViewById(R.id.register);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SignUpTask().execute();
            }
        });
    }

    class SignUpTask extends AsyncTask<String, Void, String > {
        ProgressDialog progressDialog;
        String s1,s2,s3,s4;
        Boolean chkusername;

        @Override
        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(SignUpActivity.this,
                    "Creating a new account",
                    "Loading, please wait...");
        }

        @Override
        protected String doInBackground(String... strings){
            s1 = e1.getText().toString();
            s2 = e2.getText().toString();
            s3 = e3.getText().toString();
            s4 = e4.getText().toString();
            chkusername = db.chkusername(s1);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s){
            if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")){
                Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
            }
            else{
                if(s3.equals(s4)){
                    if(chkusername == true){
                        Boolean insert = db.insert(s1, s2, s3);
                        if (insert ==true){
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "User Name Already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
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