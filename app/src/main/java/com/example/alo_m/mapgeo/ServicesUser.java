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

public class ServicesUser extends AppCompatActivity {
    ListView listView;
    List<Service> list;
    List<Service> services = new ArrayList<Service>();
    ProgressDialog progressDialog;
    ServicesAdapter servicesAdapter;
    private DatabaseReference databaseReference;
    public static final String DATABASE_PATH = "Services";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_services);

        listView = findViewById(R.id.lsv_services);

        list = new ArrayList<Service>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Please wait");
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference(ServicesActivity.DATABASE_PATH);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                // list.clear();

                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Service aService = snap.getValue(Service.class);
                    list.add(aService);
                    services.add(aService);
                }
                Log.i("Los Servicios", services.toString());
                servicesAdapter = new ServicesAdapter(ServicesUser.this, R.layout.data_items_services, list);
                listView.setAdapter(servicesAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d("List Services:", services.toString());
    }
    }

