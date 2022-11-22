package com.hkmus413f.groupproj_moviebuyer;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class Activity_Movie9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie9);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        RatingBar bar = (RatingBar)findViewById(R.id.bar);
        bar.setRating(4);
    }

    public void btn_add(View v){
        add_to_shopping_cart(1, 1, "The Gentlemen (Distributor: STX Films)", 110);
    }

    public void add_to_shopping_cart(int product_id, int count, String product_name, int price){
        SQLiteDatabase cart = openOrCreateDatabase("cart",MODE_PRIVATE,null);

        cart.execSQL("INSERT INTO cart (product_id, count, product_name, price) VALUES(" + product_id + ", " + count + ", '" + product_name + "', " + price + ");");

        Toast.makeText(getApplicationContext(), product_name + " has been added to cart.",Toast.LENGTH_SHORT).show();
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