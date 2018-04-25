package com.example.alo_m.mapgeo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityReservationPackageAdm extends Activity implements View.OnClickListener {




    String[] packages = {"Paquete 1 - Spa y Hotel", "Paquete 2 - Turistico"};
    String[] prices={"1399", "2299"};
    ArrayAdapter<String> adapter;
    Spinner spinner;


    LayoutInflater inflater1;
    int count =0;
    int price;
    String packageName, name, date, time;

    Button btnDate , btnTime;
    int dia, mes, año;
    int hour, minute;


    TextView txtPackage, txtPrice, txtDate, txtTime;
    EditText txtName, txtSearch;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    PackageReservation packageReservation;
    ListView listView;
    ArrayList<PackageReservation> packageReservationArrayList;
    CustomAdapterPackage customAdapterPackage;

    String key;

    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations_packages_adm);





        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("PackageReservations");
        key=databaseReference.push().getKey();

          btnDate = (Button)findViewById(R.id.button_date);
    btnTime = (Button)findViewById(R.id.button_time);

        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);


        txtName = (EditText) findViewById(R.id.write_name);
        txtPackage = (TextView) findViewById(R.id.write_package);
        txtDate = (TextView) findViewById(R.id.write_calendar);
        txtTime = (TextView) findViewById(R.id.write_time);
        spinner =(Spinner)findViewById(R.id.spinner_packages);
        txtPrice = (TextView) findViewById(R.id.write_price);
        listView = (ListView) findViewById(R.id.readlist);


        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, packages);
            spinner.setAdapter(adapter);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        case  0:
                            txtPackage.setText(""+ packages[i]);
                            txtPrice.setText("" + prices[i]);
                            break;
                        case  1:
                            txtPackage.setText(""+ packages[i]);
                            txtPrice.setText("" + prices[i]);
                            break;
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


          findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    try {

                        name = txtName.getText().toString().trim();


                        if (TextUtils.isEmpty(name) ) {

                            Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();

                        } else {

                            packageName = txtPackage.getText().toString().trim();
                            date = txtDate.getText().toString().trim();
                            time = txtTime.getText().toString().trim();
                            price = Integer.parseInt(txtPrice.getText().toString().trim());

                            packageReservation = new PackageReservation(databaseReference.push().getKey(), packageName, name, date, time, price);

                            databaseReference.child(packageReservation.getKey()).setValue(packageReservation);

                            Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();

                            txtPackage.setText("");
                            txtName.setText("");
                            txtDate.setText("");
                            txtTime.setText("");
                            txtPrice.setText("");

                        }
                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                    }


                }
        });

        packageReservationArrayList = new ArrayList<>();

        customAdapterPackage = new CustomAdapterPackage(ActivityReservationPackageAdm.this, packageReservationArrayList);

        listView.setAdapter(customAdapterPackage);


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                PackageReservation datam = dataSnapshot.getValue(PackageReservation.class);

                packageReservationArrayList.add(datam);

                customAdapterPackage.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                final View v = inflater1.from(getApplicationContext()).inflate(R.layout.custom_alert_package, null);
                temp = i;

                final EditText updtName, updtDate, updtTime;
                final TextView updtPackage, updtPrice;
                updtPackage = (TextView) v.findViewById(R.id.updt_package);
                updtName = (EditText) v.findViewById(R.id.updt_name);
                updtDate = (EditText) v.findViewById(R.id.updt_date);
                updtTime = (EditText) v.findViewById(R.id.updt_time);
                updtPrice = (TextView) v.findViewById(R.id.updt_price);

                final AlertDialog.Builder builder  = new AlertDialog.Builder(ActivityReservationPackageAdm.this).setView(v);
                final AlertDialog alert = builder.create();

                v.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        PackageReservation tempPackageReservation = new PackageReservation(packageReservationArrayList.get(temp).getKey(), updtPackage.getText().toString().trim(), updtName.getText().toString().trim()
                                ,updtDate.getText().toString().trim(),
                                updtTime.getText().toString().trim(), Integer.parseInt(updtPrice.getText().toString().trim()));

                        databaseReference.child(packageReservationArrayList.get(temp).getKey()).setValue(tempPackageReservation);

                        packageReservationArrayList.remove(temp);

                        packageReservationArrayList.add(temp, tempPackageReservation);

                        customAdapterPackage.notifyDataSetChanged();
                    }
                });

                v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(temp == -1){

                            Toast.makeText(getApplicationContext(), "There is no serviceReservation to delete", Toast.LENGTH_SHORT).show();

                        }else {

                            databaseReference.child(packageReservationArrayList.get(temp).getKey()).removeValue();

                            packageReservationArrayList.remove(temp);

                            customAdapterPackage.notifyDataSetChanged();

                           alert.cancel();

                            temp = -1;
                        }
                    }
                });



                updtPackage.setText(packageReservationArrayList.get(temp).getPackages());
                updtName.setText(packageReservationArrayList.get(temp).getName());
                updtDate.setText(packageReservationArrayList.get(temp).getDate());
                updtTime.setText(packageReservationArrayList.get(temp).getTime());
                updtPrice.setText("" + packageReservationArrayList.get(temp).getPrice());



                try {


                    alert.show();

                } catch (Exception e) {

                    Log.d("show", "onItemClick: " + e);

                }




                return;


            }
        });

        txtSearch = (EditText) findViewById(R.id.search);

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = txtSearch.getText().toString().trim();

                databaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        ++count;



                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                            packageReservation = dataSnapshot1.getValue(PackageReservation.class);
                            packageReservationArrayList.clear();
                            packageReservationArrayList.add(packageReservation);
                            Log.d("log", "onDataChange: "+dataSnapshot1.child("name").getValue());

                        }


                        func();






                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });








            }
        });

        realtimeUpdate();
    }

    public void realtimeUpdate(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                packageReservationArrayList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    packageReservation = dataSnapshot1.getValue(PackageReservation.class);

                    packageReservationArrayList.add(packageReservation);

                }


               customAdapterPackage.notifyDataSetChanged();


            }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}

    public void func(){

        if(count!=0){

            customAdapterPackage = new CustomAdapterPackage(getApplicationContext(), packageReservationArrayList);

            listView.setAdapter(customAdapterPackage);

        }else {

            Toast.makeText(getApplicationContext(), "There is no serviceReservation to show", Toast.LENGTH_SHORT).show();
            listView.setVisibility(View.GONE);
        }


    }

    @Override
    public void onClick(View v) {
        if (v == btnDate) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            año = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, dia, mes, año);
            datePickerDialog.show();

            //Decisión de la hora
        }
        if (v == btnTime) {
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txtTime.setText(hourOfDay + ":" + minute);
                }
            },hour, minute, true);
            timePickerDialog.show();
        }
    }


}

