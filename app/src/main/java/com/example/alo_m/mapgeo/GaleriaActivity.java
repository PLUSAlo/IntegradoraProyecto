package com.example.alo_m.mapgeo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class GaleriaActivity extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList = new ArrayList<>();
    private TextSwitcher mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        initData();
        mTitle = findViewById(R.id.title);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(GaleriaActivity.this);
                TextView txt = (TextView)inflater.inflate(R.layout.layout_title_galery,null);
                return txt;
            }
        });

        Animation in = AnimationUtils.loadAnimation(this,R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this,R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        movieAdapter = new MovieAdapter(movieList, this);
        coverFlow= (FeatureCoverFlow)findViewById(R.id.coverFlow);
        coverFlow.setAdapter(movieAdapter);

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(movieList.get(position).getName());
            }

            @Override
            public void onScrolling() {

            }
        });


    }

    private void initData() {https://

    movieList.add(new Movie("Posada Bonita", "https://78.media.tumblr.com/e5c475f9034db7a71843d7f5bb44c94d/tumblr_p6e3rj2N151xonnt8o1_540.jpg"));
        movieList.add(new Movie("Habitación", "https://78.media.tumblr.com/9d22d13b38030bb35e0423f54f935b02/tumblr_p6e3rj2N151xonnt8o2_540.jpg"));
        movieList.add(new Movie("Recepción", "https://78.media.tumblr.com/02a6596c5c10a7c7cc15709d2b064e54/tumblr_p6e3rj2N151xonnt8o3_540.jpg"));
        movieList.add(new Movie("Dolores Hidalgo", "https://78.media.tumblr.com/9d22d13b38030bb35e0423f54f935b02/tumblr_p6e3rj2N151xonnt8o4_540.jpg"));
        movieList.add(new Movie("Dolores Hidalgo", "https://78.media.tumblr.com/e84b32904dac21886e1472410a40530f/tumblr_p6e3rj2N151xonnt8o6_540.jpg"));
        movieList.add(new Movie("Dolores Hidalgo", "https://78.media.tumblr.com/c6d32265aff21fe5a6efe1bc6d75fb08/tumblr_p6e3rj2N151xonnt8o7_400.jpg"));
        movieList.add(new Movie("Dolores Hidalgo", "https://78.media.tumblr.com/078aef53b254097d523bf0832577afae/tumblr_p6e3rj2N151xonnt8o8_1280.jpg"));
        movieList.add(new Movie("Dolores Hidalgo", "https://78.media.tumblr.com/2ffe0d457e4e0c769577570eab1c663c/tumblr_p6e3rj2N151xonnt8o9_1280.jpg"));
        movieList.add(new Movie("Dolores Hidalgo", "https://78.media.tumblr.com/fe4352cd12735d6a674fd891b53b42dd/tumblr_p6e3rj2N151xonnt8o10_1280.jpg"));
    }
}