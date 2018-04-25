package com.example.alo_m.mapgeo;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class InfoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener{
//https://console.developers.google.com/apis/credentials?project=posadabonitayoutube&hl=es-419(Ubicacion de donde se encuentra las credenciales para la aplicación)
    YouTubePlayerView youTubePlayerView;
    String claveDescripcion="AIzaSyA9xcwiV8h6u9xslrkrWAupZqiE2zhC-us";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Resources res = getResources();

        TabHost tabs=findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.hotel);
        spec.setIndicator("Hotel", res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.habitacion);
        spec.setIndicator("Habitación", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.spa);
        spec.setIndicator("Spa", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);


        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(claveDescripcion, this);

    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean fueRestaurado) {
        if(!fueRestaurado){
            youTubePlayer.cueVideo("vV54EVD_HLo");//https://www.youtube.com/watch?v=vV54EVD_HLo
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,1).show();
        }else{
            String error ="Lo sentimos YouTube a tenido un error" + youTubeInitializationResult.toString();
            Toast.makeText(getApplication(),error, Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultcode, Intent data){
        if(resultcode==1){
            getYoutubePlayerProvider().initialize(claveDescripcion, this);
        }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider(){
        return youTubePlayerView;
    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }
}
