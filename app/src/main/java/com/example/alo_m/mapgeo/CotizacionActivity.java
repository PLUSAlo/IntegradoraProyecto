package com.example.alo_m.mapgeo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CotizacionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button fullPageScreenshot, customPageScreenshot;
    private LinearLayout rootContent;
    private ImageView imageView;
    private TextView hiddenText;

    //For Upload
    LayoutInflater inflater1;

    int count =0;
    String nameUser, date, time, price;
    String name;

    EditText  txtNameUser;
    TextView  txtDate,txtTime;
Spinner txtName;
    FirebaseDatabase firebaseDatabase;


    Data data;


    ListView listView;

    ArrayList<Reservation> reservationArrayList;

    ReservationAdapter reservationAdapter;

    String key;

    int temp;


    //Variables para fecha

    Button btnDate;
    TextView edtDate;
    int dia, mes, año;

    //Variables para hora
    TextView tv;
    Calendar currentTime;
    TextView edtName,  txtPrice;
    Button btnTime;
    TextView edtTime;
    Spinner spnChoose;
    int hour, minute;
    String format;


    public static final String DATABASE_PATH = "Package";
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizacion);
        findViews();
        implementClickEvents();
        databaseReference = FirebaseDatabase.getInstance().getReference(ActivityPackage.DATABASE_PATH);
        firebaseDatabase = FirebaseDatabase.getInstance();
        key=databaseReference.push().getKey();
        txtName = (Spinner) findViewById(R.id.spn_reservation);
        txtNameUser = findViewById(R.id.cliente);
        txtDate = findViewById(R.id.calendario);
        txtTime = findViewById(R.id.hora);
        txtPrice = findViewById(R.id.monto);

        //Fecha y hora

       edtName = (TextView) findViewById(R.id.monto);
        btnDate = (Button)findViewById(R.id.button_date);
        edtDate = (TextView)findViewById(R.id.calendario);
        btnTime = (Button)findViewById(R.id.button_time);
        edtTime = (TextView)findViewById(R.id.hora);
        spnChoose = (Spinner) findViewById(R.id.spn_reservation) ;

        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> areas = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.child("name").getValue(String.class);
                    areas.add(areaName);
                }

                Spinner areaSpinner = (Spinner) findViewById(R.id.spn_reservation);

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(CotizacionActivity.this, android.R.layout.simple_spinner_item, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                areaSpinner.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
/*
        findViewById(R.id.custom_page_screenshot).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                databaseReference = firebaseDatabase.getReference().child("Reservation");
                try {

                   // name = txtName.getSelectedItem().toString().trim();
                    nameUser =txtNameUser.getText().toString().trim();
                    date  =txtDate.getText().toString().trim();
                    time =txtTime.getText().toString().trim();
                    price =txtPrice.getText().toString().trim();

                    if (TextUtils.isEmpty(nameUser) ) {

                        Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();

                    } else {
                        data = new Data(databaseReference.push().getKey(), name, nameUser,date,time,price);

                        databaseReference.child(data.getKey()).setValue(data);

                        Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();

                       // txtName.getSelectedItem().toString();
                        txtNameUser.setText("");
                        txtDate.setText("");
                        txtTime.setText("");
                        txtPrice.setText("");

                    }
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                }


            }
        });
        */

    }
    @Override
    protected void onStart(){
        super.onStart();
        Query texto =  databaseReference.orderByChild("name").equalTo("Los grafos");
        texto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                final List<String> areas = new ArrayList<String>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                          String texto =  areaSnapshot.child("price").getValue(String.class);
                    edtName.setText(texto);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });
    }

    /*  Find all views Ids  */
    private void findViews() {
        customPageScreenshot = (Button) findViewById(R.id.custom_page_screenshot);

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
        switch (v.getId()) {
            case R.id.custom_page_screenshot:
                takeScreenshot(ScreenshotType.CUSTOM);
                break;
        }

        if (v == btnDate) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            año = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    edtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
                    edtTime.setText(hourOfDay + ":" + minute);
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