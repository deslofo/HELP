package com.example.klaudia.help;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


public class SMS extends ActionBarActivity implements LocationListener {

    EditText text;
    String telefon;
    LocationManager locationManager;
    Criteria criteria;
    Location location;

    public void zadzwon(View view){
        Uri number = Uri.parse("tel:" + telefon);
        Intent dial = new Intent(Intent.ACTION_CALL, number);
        startActivity(dial);
    }

    public String getBestProvider(){
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.NO_REQUIREMENT);
        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);

        String bestProvider = locationManager.getBestProvider(criteria, true);
        return bestProvider;
    }

    public void wyslij(View view){

        onLoadLocationMenager();
        String dostawca = getBestProvider();
        Log.d("dostawca", dostawca);
        location = locationManager.getLastKnownLocation(dostawca);
        Geocoder coder = new Geocoder(this);
        String localInfo = null;
        try {
            Iterator<Address> address = coder.
                    getFromLocation(location.getLatitude(),
                            location.getLongitude(), 3).iterator();
            if(address != null){
                while (address.hasNext()) {
                    Address namedLoc = address.next();
                    String placeName = namedLoc.getLocality();
                    String featueName = namedLoc.getFeatureName();
                    String country = namedLoc.getCountryName();
                    String road = namedLoc.getThoroughfare();
                    localInfo += String.format("\n[%s][%s][%s][%s]",
                            placeName, featueName, road, country);

                }
            }
            //Toast.makeText(getApplicationContext(), localInfo, Toast.LENGTH_SHORT).show();
            Log.d("localinfo", localInfo);

        }
        catch (IOException e) {
            Log.e("GPS", "Nie udalo się określić położenia" ,e);
            Toast.makeText(getApplicationContext(), "Nie udalo się określić położenia", Toast.LENGTH_SHORT).show();
        }
        String result = text.getText().toString();
        String latitude = String.valueOf(location.getLatitude());
        String longitude = String.valueOf(location.getLongitude());
        latitude.replace(",", ".");
        longitude.replace(",", ".");
        String geoUri = String.format("http://maps.google.com/maps?q=%s,%s", latitude, longitude);
        Log.d("geoUri", geoUri);
        Uri geo = Uri.parse(geoUri);
        Intent geoMap = new Intent(Intent.ACTION_VIEW, geo);
        //startActivity(geoMap);
        result += localInfo;
        result += geoUri;
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> fragmenty = null;
        fragmenty = smsManager.divideMessage(result);

        ArrayList<PendingIntent> listOfIntents = new ArrayList<PendingIntent>();
        //assert listOfIntents != null;
       // listOfIntents.add(0, generateIntent());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, geoMap, PendingIntent.FLAG_ONE_SHOT);
        listOfIntents.add(0, pendingIntent);
        smsManager.sendMultipartTextMessage(telefon, null, fragmenty, listOfIntents, null);
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        /*kr=new Criteria();
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


        geocoder = new Geocoder(this, Locale.getDefault());
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
        //smsManager = SmsManager.getDefault();
        //smsManager.sendMultipartTextMessage(telefon, null, fragmenty, null, null);
        //Toast.makeText(getApplicationContext(), "Wiadomość została wysłana", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        location = null;
        */
    }

    public void onLoadLocationMenager(){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        Bundle bundle = getIntent().getExtras();
        telefon = bundle.getString("telefon");
        text = (EditText) findViewById(R.id.text);
        Log.d("telefon", telefon);
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

    @Override
    protected void onResume(){
        super.onResume();
        if(locationManager != null)
            locationManager.requestLocationUpdates(getBestProvider(), 15000, 1f, this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        locationManager.removeUpdates(this);
    }

}
