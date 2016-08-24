package com.juliodev.taxihaiti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juliodev.taxihaiti.faceData.AfterSearch;
import com.juliodev.taxihaiti.mFirebase.Fire;
import com.juliodev.taxihaiti.model.InsertUser;

public class Findtaxi extends AppCompatActivity {


     private ProgressBar spinner;
    final static String DB_URL = "https://taxihaiti-b8535.firebaseio.com";
    Fire fire;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findtaxi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Entrer le lieu ...");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lv = (ListView) findViewById(R.id.lvMessage);
        fire = new Fire(this,lv,DB_URL);
        //fire.retriveData();

        // Hide loading
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InsertUser user = (InsertUser) lv.getItemAtPosition(position);
                if(user!=null){
                    Intent details =  new Intent(Findtaxi.this, Details.class);
                    //GESTION DU DETAISL
                    details.putExtra("user", user);
                    startActivity(details);
                }
                else {
                    Toast.makeText(Findtaxi.this, "Null "+position, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.find_taxi_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search_result);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Findtaxi.this, "Chauffeur à  " + query ,Toast.LENGTH_LONG).show();
                Intent details =  new Intent(Findtaxi.this, AfterSearch.class);
                //GESTION DU DETAISL
                startActivity(details);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fire.search(newText);
                return false ;

            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(Findtaxi.this, ListeTaxi.class);
                finish();
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
