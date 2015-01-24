package com.example.klaudia.help;

/**
 * Created by Klaudia on 2015-01-24.
 */

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.IOException;
import java.util.Iterator;


public class Apteka extends ActionBarActivity implements LocationListener {

    LocationManager locationManager;
    Criteria criteria;
    Location location;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apteka);


        onLoadLocationMenager();
        String dostawca = getBestProvider();
        Log.d("dostawca", dostawca);
        location = locationManager.getLastKnownLocation(dostawca);
        if (location != null) {
            try {
                latitude = String.valueOf(location.getLatitude());
                longitude = String.valueOf(location.getLongitude());
            } catch (Exception e) {
                Log.e("Apteka", "Nie udalo się określić położenia", e);
                Toast.makeText(getApplicationContext(), "Nie udalo się określić położenia", Toast.LENGTH_SHORT).show();
            }

            WebView webview = (WebView) findViewById(R.id.webView1);
            webview.setWebViewClient(new WebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("http://maps.google.com/maps?" + "q=apteka " + latitude + "," + longitude);


        } else
            Toast.makeText(getApplicationContext(), "Nie udalo się określić położenia2", Toast.LENGTH_SHORT).show();
    }

    public String getBestProvider(){
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.NO_REQUIREMENT);
        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);

        String bestProvider = locationManager.getBestProvider(criteria, true);
        return bestProvider;
    }

    public void onLoadLocationMenager(){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.apteka, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onLocationChanged(Location location) {
        //odswiez();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        //if(locationManager != null)
        locationManager.requestLocationUpdates(getBestProvider(), 15000, 1f, this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //if(locationManager != null)
        locationManager.removeUpdates(this);
    }
}
