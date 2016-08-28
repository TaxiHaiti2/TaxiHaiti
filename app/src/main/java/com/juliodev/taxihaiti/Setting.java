package com.juliodev.taxihaiti;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {
    TextView tvsetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Choisissez un lieu"); // set the top title
        String title = actionBar.getTitle().toString(); // get the title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //caher
        getSupportActionBar().hide();
        FloatingActionButton preference = (FloatingActionButton) findViewById(R.id.fab);
        preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvsetting = (EditText)findViewById(R.id.tvsetting);

                    Intent i = new Intent (Setting.this, ListeTaxi.class);
                    startActivity(i);
                    tvsetting.getText().toString();
                    // Share preferences
                    Toast.makeText(Setting.this, "Vous venez de définir" + tvsetting.getText().toString()+"comme préférence", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(Setting.this, ListeTaxi.class);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
