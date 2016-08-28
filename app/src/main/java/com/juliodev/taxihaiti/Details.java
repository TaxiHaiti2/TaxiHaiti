package com.juliodev.taxihaiti;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.juliodev.taxihaiti.login.Facebook_login;
import com.juliodev.taxihaiti.model.InsertUser;
import com.juliodev.taxihaiti.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    InsertUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        // set the top title
        String title = actionBar.getTitle().toString(); // get the title
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.ic_action_user);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fabTake = (FloatingActionButton) findViewById(R.id.fabTake);
        fabTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }
        });
        user = (InsertUser) getIntent().getSerializableExtra("user");
        setUpViews();
    }

    // Set up mes view
    public void setUpViews() {
        ImageView ivProfil = (ImageView) findViewById(R.id.ivProfil);
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvPhone = (TextView) findViewById(R.id.tvPhone);
        TextView tvAdresse = (TextView) findViewById(R.id.tvAdresse);
        TextView tvPlaque = (TextView) findViewById(R.id.tvPlaque);

        //Gestion de image
        ivProfil.setImageResource(0);
        String myProfileImg = user.getFacimageUrl();
        Picasso.with(this).load(myProfileImg).placeholder(R.drawable.carlogoblan).transform(new RoundedTransformation(120, 0)).centerCrop().into(ivProfil);

        tvName.setText(user.getNomcomplet().toString());
        tvPhone.setText(user.getTelephone().toString());
        tvAdresse.setText(user.getAddresse().toString());
        tvPlaque.setText(user.getPlaque().toString());

        getSupportActionBar().setTitle("" + user.getNomcomplet().toString());

        //set onclick adress webView google map extra adresse

        tvAdresse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log.d(TAG, "Tapped");
                //show the webview map
                Intent i = new Intent(Details.this, Localisation.class);
                String url = "http://www.google.com/maps/place/" + user.getAddresse().toString();
                i.putExtra("url", url);
                i.putExtra("taxiName", user.getNomcomplet().toString());
                startActivity(i);
            }
        });

        ivProfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log.d(TAG, "Tapped");
                //show the webview map
                Intent i = new Intent(Details.this, Facebook_login.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(Details.this, ListeTaxi.class);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void call() {

        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + user.getTelephone() + ""));
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                startActivity(callIntent);
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);

        } catch (ActivityNotFoundException activityException) {
            Log.e("Dialing", "Call failed");
        }
    }
}
