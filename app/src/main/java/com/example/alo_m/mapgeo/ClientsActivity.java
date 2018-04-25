package com.example.alo_m.mapgeo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClientsActivity extends Activity {

    LayoutInflater inflater1;

    int count =0;
    int phone;
    String email;
    String name;

    EditText txtName, txtEmail, txtPhone,txtSearch;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    Data data;


    ListView listView;

    ArrayList<Data> dataArrayList;

    CustomAdapter customAdapter;

    String key;

    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);



        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Clients");
        key=databaseReference.push().getKey();

        txtName = (EditText) findViewById(R.id.write_name);
        txtEmail = (EditText) findViewById(R.id.write_email);
        txtPhone = (EditText)findViewById(R.id.write_phone);
        listView = (ListView) findViewById(R.id.readlist);

          findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    try {

                        name = txtName.getText().toString().trim();
                        email =txtEmail.getText().toString().trim();

                        if (TextUtils.isEmpty(name) ) {
                            Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                        } else {

                            phone = Integer.parseInt(txtPhone.getText().toString().trim());

                            data = new Data(databaseReference.push().getKey(), name, email, phone);

                            databaseReference.child(data.getKey()).setValue(data);

                            Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();

                            txtName.setText("");
                            txtEmail.setText("");
                            txtPhone.setText("");

                        }
                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                    }


                }
        });

        dataArrayList = new ArrayList<>();

        customAdapter = new CustomAdapter(ClientsActivity.this, dataArrayList);

        listView.setAdapter(customAdapter);


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Data datam = dataSnapshot.getValue(Data.class);

                dataArrayList.add(datam);

                customAdapter.notifyDataSetChanged();
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


                final View v = inflater1.from(getApplicationContext()).inflate(R.layout.custom_alert, null);
                temp = i;

                final EditText updtName,updtEmail, updtPhone;

                updtName = (EditText) v.findViewById(R.id.updt_name);
                updtEmail = (EditText) v.findViewById(R.id.updt_email);
                updtPhone = (EditText) v.findViewById(R.id.updt_phone);


                final AlertDialog.Builder builder  = new AlertDialog.Builder(ClientsActivity.this).setView(v);
                final AlertDialog alert = builder.create();

                v.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Data tempData = new Data(dataArrayList.get(temp).getKey(), updtName.getText().toString().trim(),updtEmail.getText().toString().trim(),
                                Integer.parseInt(updtPhone.getText().toString().trim()));

                        databaseReference.child(dataArrayList.get(temp).getKey()).setValue(tempData);

                        dataArrayList.remove(temp);

                        dataArrayList.add(temp,tempData);

                        customAdapter.notifyDataSetChanged();
                    }
                });

                v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(temp == -1){

                            Toast.makeText(getApplicationContext(), "There is no data to delete", Toast.LENGTH_SHORT).show();

                        }else {

                            databaseReference.child(dataArrayList.get(temp).getKey()).removeValue();

                            dataArrayList.remove(temp);

                            customAdapter.notifyDataSetChanged();

                           alert.cancel();

                            temp = -1;
                        }
                    }
                });



                updtName.setText(dataArrayList.get(temp).getName());
                updtEmail.setText(dataArrayList.get(temp).getEmail());
                updtPhone.setText("" + dataArrayList.get(temp).getPhone());
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

                            data = dataSnapshot1.getValue(Data.class);
                            dataArrayList.clear();
                            dataArrayList.add(data);
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
                dataArrayList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    data = dataSnapshot1.getValue(Data.class);

                    dataArrayList.add(data);

                }


               customAdapter.notifyDataSetChanged();


            }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}

    public void func(){

        if(count!=0){

            customAdapter = new CustomAdapter(getApplicationContext(),dataArrayList);

            listView.setAdapter(customAdapter);

        }else {

            Toast.makeText(getApplicationContext(), "There is no data to show", Toast.LENGTH_SHORT).show();
            listView.setVisibility(View.GONE);
        }


    }
}
