package com.example.alo_m.mapgeo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alo_m on 19/04/2018.
 */

public class Reservation_Activity extends AppCompatActivity {
    public static final String DATABASE_PATH = "Package";
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizacion);

        databaseReference.child("Package").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // Is better to use a List, because you don't know the size
            // of the iterator returned by dataSnapshot.getChildren() to
            // initialize the array
            final List<String> areas = new ArrayList<String>();

            for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                String areaName = areaSnapshot.child("areaName").getValue(String.class);
                areas.add(areaName);
            }

            Spinner areaSpinner = (Spinner) findViewById(R.id.spn_reservation);
            ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(Reservation_Activity.this, android.R.layout.simple_spinner_item, areas);
            areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            areaSpinner.setAdapter(areasAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}
}
