package com.juliodev.taxihaiti;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.juliodev.taxihaiti.model.InsertUser;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaxi extends AppCompatActivity {
    private static final int TAKE_PICTURE = 1;
    private static final int CAMERA_REQUEST = 1888;
    String mCurrentPhotoPath;
    //Ajouter un utilisateur
    EditText tvName,tvPhone,tvAdresse,tvPlaque,typevehicule;
    TextView tvProfilUrl;
    ImageView ivProfil;
    static final int REQUEST_IMAGE_CAPTURE = 1;
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
        tvProfilUrl = (TextView) findViewById(R.id.tvProfilUrl);
        typevehicule = (EditText) findViewById(R.id.typevehicule);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    //CAMERA_REQUEST
    public void takePhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivProfil.setImageBitmap(imageBitmap);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.juliodev.taxihaiti",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
    private void setPic() {
        // Get the dimensions of the View
        int targetW = ivProfil.getWidth();
        int targetH = ivProfil.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        ivProfil.setImageBitmap(bitmap);
        Toast.makeText(AddTaxi.this, "ma photo " +bitmap, Toast.LENGTH_SHORT).show();
    }
    private void registerUser(){
        /*
        tvProfilUrl.setText(getIntent().getStringExtra("imageUrl"));
        tvName.setText(getIntent().getStringExtra("taxiName"));
        */
        String taxiName  = getIntent().getStringExtra("taxiName");
        String imageUrlPass  = getIntent().getStringExtra("imageUrl");
        // Toast.makeText(AddTaxi.this,"my url"+imageUrlPass,Toast.LENGTH_LONG).show();
       // imageUrlPass = tvProfilUrl.getText().toString().trim();
        final String nomUser = tvName.getText().toString().trim();
        final String phoneUser = tvPhone.getText().toString().trim();
        final String addressUser = tvAdresse.getText().toString().trim();
        final String plaqueUser = tvPlaque.getText().toString().trim().toUpperCase();;
        final String vehiculeType = typevehicule.getText().toString().trim();
        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://taxihaiti-b8535.firebaseio.com");
        if(nomUser.equals("") || phoneUser.equals("") || addressUser.equals("") || plaqueUser.equals("") || vehiculeType.equals("") ){
            Toast.makeText(AddTaxi.this,"Remplissez tous les champs",Toast.LENGTH_LONG).show();
        }
        else{
            InsertUser add = new InsertUser(nomUser,phoneUser,addressUser,plaqueUser,vehiculeType,imageUrlPass);
            ref.child("Chauffeur").push().setValue(add);
            Toast.makeText(AddTaxi.this, "Inscription réussie", Toast.LENGTH_LONG).show();
            Intent goToList = new Intent(AddTaxi.this, ListeTaxi.class);
            startActivity(goToList);
        }
    }

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
