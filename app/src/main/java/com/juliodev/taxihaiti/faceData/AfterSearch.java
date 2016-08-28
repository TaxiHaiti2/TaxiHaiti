package com.juliodev.taxihaiti.faceData;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.juliodev.taxihaiti.ListeTaxi;
import com.juliodev.taxihaiti.R;

public class AfterSearch extends AppCompatActivity {
    Button goToFakeDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Liste taxi dispo à delmas 33"); // set the top title
        String title = actionBar.getTitle().toString(); // get the title

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        goToFakeDetails= (Button)findViewById(R.id.goToFakeDetails);
        goToFakeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fakeDetails = new Intent(AfterSearch.this,FakeDetails.class);
                startActivity(fakeDetails);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(AfterSearch.this, ListeTaxi.class);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
