package com.example.alo_m.mapgeo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by alo_m on 23/04/2018.
 */

public class MenuReservationsAdm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reservations_adm);


}
    public void intentRoomAdm(View view){
        startActivity(new Intent(
                this, ActivityReservationRoomAdm.class));

    }

    public void intentPackageAdm(View view){
        startActivity(new Intent(
                this, ActivityReservationPackageAdm.class));

    }
    public void intentServiceAdm(View view){
        startActivity(new Intent(
                this, ActivityReservationServiceAdm.class));

    }

}
