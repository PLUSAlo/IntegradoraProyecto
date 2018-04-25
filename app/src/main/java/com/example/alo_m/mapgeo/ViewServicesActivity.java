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

public class ViewServicesActivity extends AppCompatActivity {
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
                servicesAdapter = new ServicesAdapter(ViewServicesActivity.this, R.layout.data_items_services, list);
                listView.setAdapter(servicesAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, int index, long l) {
                CharSequence[] items = { "Eliminar"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ViewServicesActivity.this);
                dialog.setTitle("Escoge una acción");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i==0){
                            Log.i("Service delete", servicesAdapter.list.get(adapterView.getPositionForView(view)).getId());
                            showDialogDelete(adapterView.getPositionForView(view));
                        }else {
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

        Log.d("List Servies:", services.toString());
    }

    private void showDialogDelete(final int position){
        final android.support.v7.app.AlertDialog.Builder dialogDelete = new android.support.v7.app.AlertDialog.Builder(ViewServicesActivity.this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage(" Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Service aService = services.get(position);
                    Log.i("Posicion:", ""+position);
                    DatabaseReference dR = FirebaseDatabase.getInstance().getReference(DATABASE_PATH).child(aService.getKey());
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
