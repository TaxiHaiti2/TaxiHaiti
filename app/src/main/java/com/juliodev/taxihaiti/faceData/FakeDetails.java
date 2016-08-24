package com.juliodev.taxihaiti.faceData;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.juliodev.taxihaiti.ListeTaxi;
import com.juliodev.taxihaiti.Localisation;
import com.juliodev.taxihaiti.R;

public class FakeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Isaac Samuel");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.ic_action_user);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fabFakeIssac = (FloatingActionButton) findViewById(R.id.fabFakeIssac);
        fabFakeIssac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }
        });
        TextView tvAdresse = (TextView) findViewById(R.id.tvAdresse);
        tvAdresse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log.d(TAG, "Tapped");
                //show the webview map
                Intent i = new Intent(FakeDetails.this, Localisation.class);
                String url = "http://www.google.com/maps/place/delmas 33 haiti";
                i.putExtra("url", url);
                i.putExtra("taxiName", "Isaac Samuel");
                startActivity(i);
            }
        });

    }

    private void call() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:37058306 "));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(FakeDetails.this, ListeTaxi.class);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
