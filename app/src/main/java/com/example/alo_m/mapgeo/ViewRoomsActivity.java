package com.example.alo_m.mapgeo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

public class ViewRoomsActivity extends AppCompatActivity {
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
                roomsAdapter = new RoomsAdapter(ViewRoomsActivity.this, R.layout.data_items_rooms, list);
                listView.setAdapter(roomsAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, int index, long l) {
                CharSequence[] items = { "Eliminar"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ViewRoomsActivity.this);
                dialog.setTitle("Escoge una acción");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i==0){
                            Log.i("Room delete", roomsAdapter.list.get(adapterView.getPositionForView(view)).getId());
                            showDialogDelete(adapterView.getPositionForView(view));
                        }else {
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

        Log.d("List Rooms:", rooms.toString());
    }

    private void showDialogDelete(final int position){
        final android.support.v7.app.AlertDialog.Builder dialogDelete = new android.support.v7.app.AlertDialog.Builder(ViewRoomsActivity.this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage(" Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Room aRoom = rooms.get(position);
                    Log.i("Posicion:", ""+position);
                    DatabaseReference dR = FirebaseDatabase.getInstance().getReference(DATABASE_PATH).child(aRoom.getKey());
                    Log.i("delete", dR.toString());
                    //removing artist
                    dR.removeValue();
                    Toast.makeText(getApplicationContext(), "Borrado", Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }
}
