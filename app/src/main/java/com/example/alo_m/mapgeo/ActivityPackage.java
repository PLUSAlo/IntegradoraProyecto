package com.example.alo_m.mapgeo;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by ASUS on 16/03/2018.
 */

public class ActivityPackage extends AppCompatActivity {
    ImageView imageView;
    EditText name, description, price;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    public static final String STORAGE_PATH = "images/";
    public static final String DATABASE_PATH = "Package";
    private Uri imageUri;

    private Package selectedPackage; //hold user when we select

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        imageView = (ImageView) findViewById(R.id.imv_package);
        name = (EditText) findViewById(R.id.edt_namepackage);
        description = (EditText) findViewById(R.id.edt_description);
        price = (EditText) findViewById(R.id.edt_price);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);
    }
    public void browseImages(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK){
            imageUri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getActualImage(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void uploadData(View view){

        if(imageUri != null){
            // insert data

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference reference = storageReference.child(STORAGE_PATH + System.currentTimeMillis() + "." + getActualImage(imageUri));
            reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    String NAME = name.getText().toString();
                    String DESCRIPTION = description.getText().toString();
                    String PRICE = price.getText().toString();
                    Package apackage = new Package(UUID.randomUUID().toString(),NAME,DESCRIPTION,PRICE,taskSnapshot.getDownloadUrl().toString());
                    String id = databaseReference.push().getKey();

                    apackage.setKey(id);
                    Log.i("La clave", apackage.getKey());
                    databaseReference.child(id).setValue(apackage);
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Data uploaded",Toast.LENGTH_LONG).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double totalProgress = (100*taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded % " + (int)totalProgress);
                        }
                    });
        } else {
            // show message
            Toast.makeText(getApplicationContext(),"Please select data first",Toast.LENGTH_LONG).show();
        }

    }

    public void viewAllData(View view){
        Intent intent = new Intent(ActivityPackage.this, ViewPackageActivity.class);
        startActivity(intent);
    }
}

