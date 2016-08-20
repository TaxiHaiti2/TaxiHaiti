package com.juliodev.taxihaiti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.juliodev.taxihaiti.model.InsertUser;

import java.io.File;

public class AddTaxi extends AppCompatActivity {
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    //Ajouter un utilisateur
    EditText tvName,tvPhone,tvAdresse,tvPlaque,typevehicule;
    ImageView ivProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_taxi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("S'inscrire"); // set the top title
        String title = actionBar.getTitle().toString(); // get the title
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Gestion d'ajout utilisateur

        tvName = (EditText) findViewById(R.id.tvName);
        tvPhone = (EditText) findViewById(R.id.tvPhone);
        tvAdresse = (EditText) findViewById(R.id.tvAdresse);
        tvPlaque = (EditText) findViewById(R.id.tvPlaque);
        ivProfil = (ImageView) findViewById(R.id.ivProfil);
        typevehicule = (EditText) findViewById(R.id.typevehicule);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    private void registerUser(){
        final String nomUser = tvName.getText().toString().trim();
        final String phoneUser = tvPhone.getText().toString().trim();
        final String addressUser = tvAdresse.getText().toString().trim();
        final String plaqueUser = tvPlaque.getText().toString().trim().toUpperCase();;
        final String vehiculeType = typevehicule.getText().toString().trim();
        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://taxihaiti-b8535.firebaseio.com");
        if(nomUser.equals("") || phoneUser.equals("") || addressUser.equals("") || plaqueUser.equals("") || vehiculeType.equals("")   ){
            Toast.makeText(AddTaxi.this,"Remplissez tous les champs",Toast.LENGTH_LONG).show();
        }
        else{
            InsertUser add = new InsertUser(nomUser,phoneUser,addressUser,plaqueUser,vehiculeType );
            ref.child("Chauffeur").push().setValue(add);
            Toast.makeText(AddTaxi.this, "Inscription reussie", Toast.LENGTH_LONG).show();
            Intent goToList = new Intent(AddTaxi.this, ListeTaxi.class);
            startActivity(goToList);
        }

    }

    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ContentResolver cr = getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);

                        ivProfil.setImageBitmap(bitmap);
                        Toast.makeText(this, selectedImage.toString(),
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(AddTaxi.this, ListeTaxi.class);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
