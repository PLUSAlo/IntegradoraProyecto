package com.example.alo_m.mapgeo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.telecom.Call;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    /*User Profile*/
    public void intentUser(View view){
        startActivity(new Intent(
                this, UserProfile.class));

    }

    public void intentGalery(View view){
        startActivity(new Intent(
                this, GaleriaActivity.class));

    }

    public void intentHelp(View view){
        startActivity(new Intent(
                this, HelpActivity.class));

    }
    public void intentComment(View view){
        startActivity(new Intent(
                this, CommentActivity.class));

    }
    public void intentListPackets(View view){
        startActivity(new Intent(
                this, ListPackageActivity.class));

    }

    public void intentInfo(View view){
        startActivity(new Intent(
                this, InfoActivity.class));

    }
     public void intentOffers(View view){
        startActivity(new Intent(
                this, OfftersUser.class));

    }

    public void intentServices(View view){
        startActivity(new Intent(
                this, ServicesUser.class));

    }
    public void intentRooms(View view){
        startActivity(new Intent(
                this, RoomsUser.class));

    }

    public void intentMap(View view){
        startActivity(new Intent(
                this, MapsActivity.class));

    }

    public void intentContact(View view){
        startActivity(new Intent(
                this, CallActivity.class));
    }
    public void intentWeather(View view){
        startActivity(new Intent(
                this,WeatherActivity.class));
    }

    public void intentFood(View view){
        startActivity(new Intent(
                this, ListActivity.class));
    }
    public void intentPlaces(View view){
        startActivity(new Intent(
                this, PlacesListActivity.class));
    }

    public void intent360(View view){
        startActivity(new Intent(
                this, Room360.class));
    }

    public void intentReservation(View view){
        startActivity(new Intent(
                this, MenuReservationsUser.class));
    }
}
