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

public class OfftersUser extends AppCompatActivity {
    ListView listView;
    List<Offer> list;
    List<Offer> offers = new ArrayList<Offer>();
    ProgressDialog progressDialog;
    OffersAdapter offersAdapter;
    private DatabaseReference databaseReference;
    public static final String DATABASE_PATH = "Offers";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_offers);

        listView = findViewById(R.id.lsv_offers);

        list = new ArrayList<Offer>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Please wait");
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference(ActivityOffers.DATABASE_PATH);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                // list.clear();

                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Offer aOffer = snap.getValue(Offer.class);
                    list.add(aOffer);
                    offers.add(aOffer);
                }
                Log.i("Las ofertas", offers.toString());
                offersAdapter = new OffersAdapter(OfftersUser.this, R.layout.data_items_offers, list);
                listView.setAdapter(offersAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d("List Offers:", offers.toString());
    }
    }

