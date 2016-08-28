package com.juliodev.taxihaiti;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juliodev.taxihaiti.mFirebase.Fire;
import com.juliodev.taxihaiti.model.InsertUser;

public class ListeTaxi extends AppCompatActivity {
    private ProgressBar spinner;
    final static String DB_URL = "https://taxihaiti-b8535.firebaseio.com/";
    Fire fire;
    ListView lv;
    EditText tvsetting;
    Button pref;

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

       /* String userEmail = getIntent().getStringExtra("email");

        if(userEmail.equals("")){
        }else{
            getSupportActionBar().setTitle(userEmail);
        }
        */
        spinner = (ProgressBar)findViewById(R.id.progressBar1);

        lv = (ListView) findViewById(R.id.lvMessage);
        fire = new Fire(this,lv,DB_URL);
        fire.retriveData();
        // Hide loading

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InsertUser user = (InsertUser) lv.getItemAtPosition(position);
                if(user!=null){
                    Intent details =  new Intent(ListeTaxi.this, Details.class);
                    //GESTION DU DETAISL
                    details.putExtra("user", user);
                    startActivity(details);
                }
                else {
                    Toast.makeText(ListeTaxi.this, "Null "+position, Toast.LENGTH_LONG).show();
                }
            }
        });
        //spinner.setVisibility(View.GONE);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                showDialogue();
            }
        });

       /*
            Bundle inBundle = getIntent().getExtras();
            String name = inBundle.get("name").toString();
            String surname = inBundle.get("surname").toString();
            String imageUrl = inBundle.get("imageUrl").toString();
        */
    }
    //Show the dialog
    private void showDialogue (){
        Dialog d =  new Dialog(this);
        d.setTitle("Preference");
        d.setContentView(R.layout.dialogsetting);
        tvsetting = (EditText)d.findViewById(R.id.tvsetting);
        pref = (Button)d.findViewById(R.id.btVvalPrefbtVvalPref);
        pref.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String settingText = tvsetting.getText().toString();
                      if(settingText.equals("")){
                          Toast.makeText(ListeTaxi.this, "Entrer une zone de préférence ", Toast.LENGTH_SHORT).show();
                      }else{
                          Intent i = new Intent(ListeTaxi.this, ListeTaxi.class);
                          i.putExtra("zone",settingText);
                          startActivity(i);
                          tvsetting.getText().toString();
                          // Share preferences
                          Toast.makeText(ListeTaxi.this, "Vous venez de definir " + tvsetting.getText().toString() + " comme preference", Toast.LENGTH_LONG).show();
                      }
                  }
              });
        d.show();
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
    }


}
