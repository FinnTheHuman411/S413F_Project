package com.hkmus413f.groupproj_moviebuyer;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class Activity_Movie11 extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {

    private static final String VIDEO_ID = "Zi4LMpSDccc";
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    YouTubePlayerFragment myYouTubePlayerFragment;
    Fragment myFragment;
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

    public void ShowPreview(Fragment fragment){
        ft.show(myFragment);
    }

    public void HidePreview(Fragment fragment){
        ft.hide(myFragment);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie11);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        RatingBar bar = (RatingBar)findViewById(R.id.bar);
        bar.setRating(5);

        myYouTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager().findFragmentById(R.id.youtube_fragment);
        myFragment = getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
        myYouTubePlayerFragment.initialize(YTConfig.getAPI_Key(), this);

    }

    public void btn_add(View v){
        add_to_shopping_cart(11, 1, "Frozen II", 71, R.drawable.m11);
    }

    public void add_to_shopping_cart(int product_id, int count, String product_name, int price, int image){
        SQLiteDatabase cart = openOrCreateDatabase("cart",MODE_PRIVATE,null);

        cart.execSQL("INSERT INTO cart (product_id, count, product_name, price, image) VALUES(" + product_id + ", " + count + ", '" + product_name + "', " + price + ", " + image +" );");

        Toast.makeText(getApplicationContext(), product_name + " has been added to cart.",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "There was an error initializing the YouTube Player (%1$s)",
                    errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(YTConfig.getAPI_Key(), this);
        }
    }
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView)findViewById(R.id.youtube_fragment);
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