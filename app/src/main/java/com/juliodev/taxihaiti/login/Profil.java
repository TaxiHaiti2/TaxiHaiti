package com.juliodev.taxihaiti.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.juliodev.taxihaiti.AddTaxi;
import com.juliodev.taxihaiti.ListeTaxi;
import com.juliodev.taxihaiti.R;
import com.juliodev.taxihaiti.SplashScreen;
import com.squareup.picasso.Picasso;

public class Profil extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_action_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inscrire();
            }
        });
        FloatingActionButton fabListNext = (FloatingActionButton) findViewById(R.id.fabListNext);
        fabListNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profil.this, ListeTaxi.class);
                startActivity(i);
            }
        });



        Bundle inBundle = getIntent().getExtras();
        String name = inBundle.get("name").toString();
        setTitle(name); // get the title
        String surname = inBundle.get("surname").toString();
        String imageUrl = inBundle.get("imageUrl").toString();
        ImageView ivProfil = (ImageView) findViewById(R.id.ivProfil);
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvPhone = (TextView) findViewById(R.id.tvPhone);
        TextView tvAdresse = (TextView) findViewById(R.id.tvAdresse);
        TextView tvPlaque = (TextView) findViewById(R.id.tvPlaque);
        tvName.setText("" + name + " " + surname);
        //Gestion de image
        ivProfil.setImageResource(0);
        Picasso.with(this).load(imageUrl).placeholder(R.drawable.carlogoblan).into(ivProfil);
    }

    public void Inscrire(){
        Bundle inBundle = getIntent().getExtras();
        String name = inBundle.get("name").toString();
        setTitle(name); // get the title
        String surname = inBundle.get("surname").toString();
        String imageUrl = inBundle.get("imageUrl").toString();

        Intent InscrireData = new Intent(Profil.this, AddTaxi.class);
        InscrireData.putExtra("name",name);
        InscrireData.putExtra("surname",surname);
        InscrireData.putExtra("imageUrl",imageUrl);
        startActivity(InscrireData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profil_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out_btn) {
            logout();
            return true;
        }
        else if (id == R.id.home){
            Intent i = new Intent(Profil.this, ListeTaxi.class);
            startActivity(i);
            Toast.makeText(this,"click sur profil",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        LoginManager.getInstance().logOut();
        Intent login = new Intent(Profil.this, SplashScreen.class);
        startActivity(login);
        finish();
    }
}
