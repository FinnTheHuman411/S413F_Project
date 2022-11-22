package com.hkmus413f.groupproj_moviebuyer;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePageViewHolder {

    ImageView itemImage;
    TextView MovieTitle;
    TextView MoviePrice;
    TextView MovieGenre;
    HomePageViewHolder(View v){
        itemImage = v.findViewById(R.id.icon);
        MovieTitle = v.findViewById(R.id.MovieName);
        MoviePrice = v.findViewById(R.id.MoviePrice);
        MovieGenre = v.findViewById(R.id.MovieGenre);
    }
}
