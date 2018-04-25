package com.example.alo_m.mapgeo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by alo_m on 22/04/2018.
 */

public class ActivityReservationPackageUser extends Activity implements View.OnClickListener {

    private Button customPageScreenshot;
    private LinearLayout rootContent;
    private ImageView imageView;
    private TextView hiddenText;


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
    EditText txtName;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    PackageReservation packageReservation;



    ArrayList<PackageReservation> packageReservationArrayList;

    CustomAdapterPackage customAdapterPackage;

    String key;

    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations_packages_user);
        findViews();
        implementClickEvents();





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
                        takeScreenshot(ScreenshotType.CUSTOM);

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

        customAdapterPackage = new CustomAdapterPackage(ActivityReservationPackageUser.this, packageReservationArrayList);



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

    private void findViews() {
        customPageScreenshot = (Button) findViewById(R.id.submit);

        rootContent = (LinearLayout) findViewById(R.id.root_content);

        imageView = (ImageView) findViewById(R.id.image_view);

        hiddenText = (TextView) findViewById(R.id.hidden_text);
    }

    /*  Implement Click events over Buttons */
    private void implementClickEvents() {
        customPageScreenshot.setOnClickListener(this);
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



    /*  Method which will take screenshot on Basis of Screenshot Type ENUM  */
    private void takeScreenshot(ScreenshotType screenshotType) {
        Bitmap b = null;
        switch (screenshotType) {
            case CUSTOM:
                //If Screenshot type is CUSTOM

                hiddenText.setVisibility(View.VISIBLE);//set the visibility to VISIBLE of hidden text

                b = ScreenshotUtils.getScreenShot(rootContent);

                //After taking screenshot reset the button and view again
                hiddenText.setVisibility(View.INVISIBLE);//set the visibility to INVISIBLE of hidden text

                //NOTE:  You need to use visibility INVISIBLE instead of GONE to remove the view from frame else it wont consider the view in frame and you will not get screenshot as you required.
                break;
        }

        //If bitmap is not null
        if (b != null) {
            showScreenShotImage(b);//show bitmap over imageview

            File saveFile = ScreenshotUtils.getMainDirectoryName(this);//get the path to save screenshot
            File file = ScreenshotUtils.store(b, "screenshot" + screenshotType + ".jpg", saveFile);//save the screenshot to selected path
            shareScreenshot(file);//finally share screenshot
        } else
            //If bitmap is null show toast message
            Toast.makeText(this, R.string.screenshot_take_failed, Toast.LENGTH_SHORT).show();

    }

    /*  Show screenshot Bitmap */
    private void showScreenShotImage(Bitmap b) {
        imageView.setImageBitmap(b);
    }

    /*  Share Screenshot  */
    private void shareScreenshot(File file) {
        Uri uri = Uri.fromFile(file);//Convert file path into Uri for sharing
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharing_text));
        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
    }

}

