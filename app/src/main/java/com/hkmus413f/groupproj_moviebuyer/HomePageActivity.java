package com.hkmus413f.groupproj_moviebuyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    GridView gridView;

    String MovieName[] = new String[]{
            "Laid-Back Camp Movie",
            "F9: The Fast Saga",
            "The Fast And The Furious: Tokyo Drift",
            "My Little Pony: The Movie",
            "Initial D",
            "YuruYuri Nachuyachumi",
    };
    String MovieGenre[] = new String[]{
            "Slice of life ",
            "Action",
            "Racing",
            "Fantasy",
            "Racing",
            "Comedy",
    };
    String MoviePrice[] = new String[]{
            "$80",
            "$70",
            "$45",
            "$55",
            "$33",
            "$49",
    };
    int MovieImages[] = new int[]{
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m4,
            R.drawable.m5,
            R.drawable.m6
    };

    List<MovieModel> listItems = new ArrayList<>();
    HomePageAdapter homePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if(getIntent().getExtras() != null){
            showInfo();
        }

        gridView = findViewById(R.id.gridview);

        for (int i = 0; i < MovieName.length; i++){
            MovieModel movieModel = new MovieModel(MovieImages[i], MovieName[i], MoviePrice[i], MovieGenre[i]);
            listItems.add(movieModel);
        }

        homePageAdapter = new HomePageAdapter(listItems,this);
        gridView.setAdapter(homePageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = listItems.indexOf(homePageAdapter.getItem(position));

                if (pos == 0) {
                    Intent intent = new Intent(view.getContext(), Activity_Movie1.class);
                    startActivity(intent);
                }

                if (pos == 1) {
                    Intent intent = new Intent(view.getContext(), Activity_Movie2.class);
                    startActivity(intent);
                }

                if (pos == 2) {
                    Intent intent = new Intent(view.getContext(), Activity_Movie3.class);
                    startActivity(intent);
                }

                if (pos == 3) {
                    Intent intent = new Intent(view.getContext(), Activity_Movie4.class);
                    startActivity(intent);
                }

                if (pos == 4) {
                    Intent intent = new Intent(view.getContext(), Activity_Movie5.class);
                    startActivity(intent);
                }

                if (pos == 5) {
                    Intent intent = new Intent(view.getContext(), Activity_Movie6.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.homepagemenu, menu);
        MenuItem searchBar = menu.findItem(R.id.search_bar);
        MenuItem logoutButton = menu.findItem(R.id.logout);

        SearchView searchView = (SearchView) searchBar.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                homePageAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.search_bar){
            return true;
        } else if (id == R.id.logout){
            finish();
            return true;
        } else if (id == R.id.user_info){
            final String key_username = getIntent().getStringExtra("username");   //Pass from LoginActivity
            Intent i = new Intent(HomePageActivity.this, UserInfoActivity.class);
            i.putExtra("username",key_username);
            startActivity(i);
            return true;
        } else if (id == R.id.about_app){
            Intent i = new Intent(HomePageActivity.this, AboutActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class HomePageAdapter extends BaseAdapter implements Filterable {

        private List<MovieModel> movieModelList;
        private List<MovieModel> movieModelListFiltered;
        private Context context;

        public HomePageAdapter(List<MovieModel> movieModelList, Context context){
            this.movieModelList = movieModelList;
            this.movieModelListFiltered = movieModelList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return movieModelListFiltered.size();
        }

        @Override
        public Object getItem(int i) {
            return movieModelListFiltered.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View conview, ViewGroup viewGroup) {
            View view = getLayoutInflater().inflate(R.layout.movie_preview_grid, null);

            ImageView imageView = view.findViewById(R.id.icon);
            TextView movieName = view.findViewById(R.id.MovieName);
            TextView moviePrice = view.findViewById(R.id.MoviePrice);
            TextView movieGenre = view.findViewById(R.id.MovieGenre);

            imageView.setImageResource(movieModelListFiltered.get(i).getImage());
            movieName.setText(movieModelListFiltered.get(i).getMovieName());
            moviePrice.setText(movieModelListFiltered.get(i).getMoviePrice());
            movieGenre.setText(movieModelListFiltered.get(i).getMovieGenre());

            return view;
        }


        @Override
        public Filter getFilter(){
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterResults = new FilterResults();
                    if(charSequence == null || charSequence.length() == 0){
                        filterResults.count = movieModelList.size();
                        filterResults.values = movieModelList;
                    } else {
                        String searchStr = charSequence.toString().toLowerCase();
                        List<MovieModel> resultData = new ArrayList<>();
                        for(MovieModel movieModel:movieModelList){
                            if(movieModel.getMovieName().toLowerCase().contains(searchStr)||movieModel.getMovieGenre().toLowerCase().contains(searchStr)){
                                resultData.add(movieModel);
                            }
                            filterResults.count = resultData.size();
                            filterResults.values = resultData;
                        }
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    movieModelListFiltered = (List<MovieModel>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }

    public void showInfo(){
        DatabaseHelper db = new DatabaseHelper(this);
        final String key_username = getIntent().getStringExtra("username");   //Pass from LoginActivity
        Cursor res = db.getInfo(key_username);
        if (res.getCount() == 0){
            Toast.makeText(HomePageActivity.this, "NO INFO", Toast.LENGTH_SHORT).show();
            return ;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n");
            buffer.append("Password: " + res.getString(2) + "\n");
            buffer.append("Age: " + res.getString(3) + "\n");
            buffer.append("Credit: " + res.getString(4) + "\n");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Entries");
        builder.setMessage(buffer.toString());
        builder.show();
    }

    public void goToShoppingCart(View v){
        Intent i = new Intent(this, ShoppingCartActivity.class);
        startActivity(i);
    }
}