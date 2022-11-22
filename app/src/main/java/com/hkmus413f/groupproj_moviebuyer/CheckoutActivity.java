package com.hkmus413f.groupproj_moviebuyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {


    public int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SQLiteDatabase cart = openOrCreateDatabase("cart",MODE_PRIVATE,null);
        Cursor resultSet = cart.rawQuery("Select * from cart",null);
        resultSet.moveToFirst();
        if (resultSet.getCount() != 0){
            do{
                totalPrice += resultSet.getInt(4);
            } while (resultSet.moveToNext());
        }

        TextView tv_totalPrice = (TextView) findViewById(R.id.totalPrice);
        tv_totalPrice.setText("Total Price: $" + totalPrice);
    }

    public void btn_checkout(View v){
        Intent i = new Intent(this, SummaryActivity.class);
        i.putExtra("totalPrice", totalPrice);
        startActivity(i);
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