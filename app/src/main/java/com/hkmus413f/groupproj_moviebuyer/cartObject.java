package com.hkmus413f.groupproj_moviebuyer;


import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.media.Image;

public class cartObject {
    public int cartID, product_id;
    public String product_name;
    public int count, price;
    public int image;

    public cartObject(int cartID, int product_id, String product_name, int count, int price, int image){
        this.cartID = cartID;
        this.product_id = product_id;
        this.product_name = product_name;
        this.count = count;
        this.price = price;
        this.image = image;
    }

}
