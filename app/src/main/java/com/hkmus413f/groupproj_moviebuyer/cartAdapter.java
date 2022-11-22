package com.hkmus413f.groupproj_moviebuyer;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class cartAdapter extends ArrayAdapter<cartObject> {

    public Context mContext;

    public cartAdapter(@NonNull Context context, ArrayList<cartObject> cartObjects) {
        super(context, 0, cartObjects);
        mContext = context;
    }

    @Override

    public View getView(int position, View convertView, final ViewGroup parent) {

        // Get the data item for this position
        final cartObject obj = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cardview, parent, false);
        }

        // Lookup view for data population
        ImageView tv_product_image = (ImageView) convertView.findViewById(R.id.product_image);
        TextView tv_product_name = (TextView) convertView.findViewById(R.id.product_name);
        final TextView tv_price = (TextView) convertView.findViewById(R.id.price);
        final TextView tv_count = (TextView) convertView.findViewById(R.id.count);

        // Populate the data into the template view using the data object
        tv_product_image.setImageResource(obj.image);
        tv_product_name.setText(obj.product_name);
        tv_price.setText("Price: $" + obj.price);
        tv_count.setText("Count: " + obj.count);

        //button
        com.google.android.material.button.MaterialButton btn_remove = (com.google.android.material.button.MaterialButton) convertView.findViewById(R.id.btn_remove);

        // Cache row position inside the button using `setTag`
        btn_remove.setTag(position);

        // Attach the click event handler
        btn_remove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                // Access the row position here to get the correct data item
                cartObject object = getItem(position);
                remove(object);

                SQLiteDatabase cart = mContext.openOrCreateDatabase("cart",mContext.MODE_PRIVATE,null);
                cart.delete("cart", "_id = " + object.cartID, null);
            }
        });

        // Return the completed view to render on screen
        return convertView;

    }


}