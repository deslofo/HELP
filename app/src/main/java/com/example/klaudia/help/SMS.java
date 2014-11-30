package com.example.klaudia.help;

import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SMS extends ActionBarActivity implements LocationListener {

    EditText text;
    ArrayList<String> fragmenty = null;
    //SmsManager smsManager = null;
    String telefon = "881204283";
    //bla

    LocationManager locationManager;
    Location location;
    String najlepszydostawca;
    Criteria kr;
    Geocoder geocoder;
    double dlugosc;
    double szerokosc;
    boolean gps = false;
    boolean internet = false;
    Location savedLocation = null;

    public void zadzwon(View view){
        Uri number = Uri.parse("tel:" + telefon);
        Intent dial = new Intent(Intent.ACTION_CALL, number);
        startActivity(dial);
    }

    public void wyslij(View view){

        kr=new Criteria();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        najlepszydostawca = locationManager.getBestProvider(kr, true);
        Log.d("dlugosc", najlepszydostawca);
        gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        internet = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if(!gps) {
            if(internet) {
                //odswiez();
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this);
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                dlugosc = location.getLongitude();
                Log.d("dlugosc", String.valueOf(location.getLongitude()));
                szerokosc = location.getLatitude();
                Log.d("szerokosc", String.valueOf(location.getLatitude()));
            }
        }
        else{
            //odswiez();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, ll);
            savedLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            dlugosc = savedLocation.getLongitude();
            Log.d("dlugosc", String.valueOf(savedLocation.getLongitude()));
            szerokosc = savedLocation.getLatitude();
            Log.d("szerokosc", String.valueOf(savedLocation.getLatitude()));
            //Log.d("szerokosc", "nie udalo sie");
        }


        geocoder = geocoder = new Geocoder(this, Locale.getDefault());;
        List<Address> addresses;
        String result = "";
        try{
            addresses = geocoder.getFromLocation(szerokosc, dlugosc, 1);
            //String result = "Geolocation address:\n";
            for (Address address : addresses) {
                for(int i = 0, j = address.getMaxAddressLineIndex(); i <= j; i++)
                    result += address.getAddressLine(i) + "\n";
                result += "\n\n";
            }
        }
        catch (IOException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

        String s = text.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        s += " " + result;
        Log.d("dlugosc", s);
        fragmenty = smsManager.divideMessage(s);
        smsManager = SmsManager.getDefault();
        smsManager.sendMultipartTextMessage(telefon, null, fragmenty, null, null);
        //Toast.makeText(getApplicationContext(), "Wiadomość została wysłana", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        location = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        Bundle bundle = getIntent().getExtras();
        telefon = bundle.getString("telefon");
        Log.d("telefon", telefon);
        text = (EditText) findViewById(R.id.text);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sm, menu);
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

    private LocationListener ll = new LocationListener() {
        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) { }

        @Override
        public void onProviderEnabled(String arg0) { }

        @Override
        public void onProviderDisabled(String arg0) {

        }

        @Override
        public void onLocationChanged(Location location) {


            if(savedLocation == null)
                savedLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    };

    @Override
    protected void onStop() {

        if (locationManager!=null) {
            locationManager.removeUpdates(ll);
            locationManager.removeUpdates(ll);
            locationManager.removeUpdates(this);
        }
        super.onStop();
    }

}
