package com.hkmus413f.groupproj_moviebuyer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuestActivity extends AppCompatActivity {

    EditText e1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        e1 = (EditText)findViewById(R.id.et_guest_email);
        b1 = (Button) findViewById(R.id.btn_submit_email);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = e1.getText().toString();
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);

                if (matcher.matches() == true) {
                    new GuestLoginTask().execute();
                }
                else{
                    Toast.makeText(GuestActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class GuestLoginTask extends AsyncTask<String, Void, String > {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(GuestActivity.this,
                    "Logging In",
                    "Loading, please wait...");
        }

        @Override
        protected String doInBackground(String... strings){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s){
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(GuestActivity.this, HomePageActivity.class);      //if login success then jump to Main page
            startActivity(i);
            finish();
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