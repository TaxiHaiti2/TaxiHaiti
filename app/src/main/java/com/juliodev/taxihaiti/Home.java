package com.juliodev.taxihaiti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    final static String DB_URL = "https://taxihaiti-b8535.firebaseio.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Gestion du login avec google auth ou facebook
    }
}

