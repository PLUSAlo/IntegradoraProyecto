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

public class MainActivity2 extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

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
                        Toast.makeText(MainActivity2.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity2.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void intentUser(View view){
        startActivity(new Intent(
                this, UserProfile.class));

    }
    public void intentClients(View view){
        startActivity(new Intent(
                this, ClientsActivity.class));

    }
    public void intentPackets(View view){
        startActivity(new Intent(
                this, ActivityPackage.class));

    }
    public void intentOffers(View view){
        startActivity(new Intent(
                this, ActivityOffers.class));

    }

    public void intentServices(View view){
        startActivity(new Intent(
                this, ServicesActivity.class));

    }
    public void intentRooms(View view){
        startActivity(new Intent(
                this, RoomsActivity.class));

    }
    public void intentCommentList(View view){
        startActivity(new Intent(
                this, ListCommentActivity.class));

    }

    public void intentMenuAdm(View view){
        startActivity(new Intent(
                this, MenuReservationsAdm.class));

    }
}