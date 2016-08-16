package com.juliodev.taxihaiti;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class ListeTaxi extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_taxi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // gestion de bar
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("TaxiHaiti"); // set the top title
        String title = actionBar.getTitle().toString(); // get the title
        getSupportActionBar().setLogo(R.drawable.ic_action_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTaxi =  new Intent(ListeTaxi.this,AddTaxi.class);
                startActivity(addTaxi);
                overridePendingTransition(0,0);
            }
        });
        FloatingActionButton fabSetting = (FloatingActionButton) findViewById(R.id.fabSetting);
        fabSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTaxi =  new Intent(ListeTaxi.this,Setting.class);
                startActivity(addTaxi);
                overridePendingTransition(0,0);
            }
        });

        ImageView ivImage1 = (ImageView) findViewById(R.id.ivImage1);
        ivImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(ListeTaxi.this, Details.class);
                startActivity(details);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.liste_taxi_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.find_button:
                onFind();
                return true;
           /* case R.id.profil:
                onProfileView();
                return true;*/

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onFind() {
        Intent find =  new Intent(ListeTaxi.this,Findtaxi.class);
        startActivity(find);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }


}
