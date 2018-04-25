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
 * Created by ASUS on 16/03/2018.
 */

public class ListPackageActivity extends AppCompatActivity {
    ListView listView;
    List<Package> list;
    List<Package> packages = new ArrayList<Package>();
    ProgressDialog progressDialog;
    PackageAdapter packageAdapter;
    private DatabaseReference databaseReference;
    public static final String DATABASE_PATH = "Package";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_package);

        listView = (ListView) findViewById(R.id.lsv_package);

        list = new ArrayList<Package>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Please wait");
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference(ActivityPackage.DATABASE_PATH);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                // list.clear();

                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Package aPackage = snap.getValue(Package.class);
                    list.add(aPackage);
                    packages.add(aPackage);
                }
                Log.i("Los paquetes", packages.toString());
                packageAdapter = new PackageAdapter(ListPackageActivity.this, R.layout.data_items, list);
                listView.setAdapter(packageAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Log.d("List packages:", packages.toString());
    }


}

