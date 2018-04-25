package com.example.alo_m.mapgeo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by alo_m on 23/04/2018.
 */

public class MenuReservationsUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reservations_user);


}
    public void intentRoomUser(View view){
        startActivity(new Intent(
                this, ActivityReservationRoomUser.class));

    }

    public void intentPackageUser(View view){
        startActivity(new Intent(
                this, ActivityReservationPackageUser.class));

    }
    public void intentServiceUser(View view){
        startActivity(new Intent(
                this, ActivityReservationServiceUser.class));

    }

}
