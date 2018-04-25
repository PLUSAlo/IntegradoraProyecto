package com.example.alo_m.mapgeo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio on 18/04/2018.
 */

public class RoomsUser extends AppCompatActivity {
    ListView listView;
    List<Room> list;
    List<Room> rooms = new ArrayList<Room>();
    ProgressDialog progressDialog;
    RoomsAdapter roomsAdapter;
    private DatabaseReference databaseReference;
    public static final String DATABASE_PATH = "Rooms";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rooms);

        listView = findViewById(R.id.lsv_rooms);

        list = new ArrayList<Room>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Please wait");
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference(RoomsActivity.DATABASE_PATH);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                // list.clear();

                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Room aRoom = snap.getValue(Room.class);
                    list.add(aRoom);
                    rooms.add(aRoom);
                }
                Log.i("Las Habitaciones", rooms.toString());
                roomsAdapter = new RoomsAdapter(RoomsUser.this, R.layout.data_items_rooms, list);
                listView.setAdapter(roomsAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d("List Rooms:", rooms.toString());
    }
    }

