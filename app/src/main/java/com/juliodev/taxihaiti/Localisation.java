package com.juliodev.taxihaiti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Localisation extends AppCompatActivity {
ProgressBar spinnerLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localisation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        // set the top title
        String title = actionBar.getTitle().toString(); // get the title
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.ic_action_user);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String taxiName  = getIntent().getStringExtra("taxiName");
        getSupportActionBar().setTitle("Position de " +taxiName);
        //progress bar
        spinnerLocation = (ProgressBar)findViewById(R.id.progressBarLocation);

        // gestion de map
        String url  = getIntent().getStringExtra("url");
        WebView webView =  (WebView) findViewById(R.id.webViewLocalisation);

         webView.setWebViewClient(new WebViewClient(){

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    view.loadUrl(url);
                    spinnerLocation.setVisibility(View.GONE);
                    return  true;

                }
            });
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(Localisation.this, ListeTaxi.class);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
