package com.hkmus413f.groupproj_moviebuyer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class summaryAdapter  extends ArrayAdapter<cartObject> {
    public Context mContext;

    public summaryAdapter(@NonNull Context context, ArrayList<cartObject> cartObjects) {
        super(context, 0, cartObjects);
        mContext = context;

    }

    @Override

    public View getView(int position, View convertView, final ViewGroup parent) {

        // Get the data item for this position
        final cartObject obj = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_summary, parent, false);
        }

        // Lookup view for data population
        TextView tv_product_name = (TextView) convertView.findViewById(R.id.summary_product_name);
        final TextView tv_price = (TextView) convertView.findViewById(R.id.summary_price);
        final TextView tv_count = (TextView) convertView.findViewById(R.id.summary_count);

        // Populate the data into the template view using the data object
        tv_product_name.setText(obj.product_name);
        tv_price.setText("Price: $" + obj.price);
        tv_count.setText("Count: " + obj.count);


        // Return the completed view to render on screen
        return convertView;

    }
}
