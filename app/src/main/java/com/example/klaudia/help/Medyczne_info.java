package com.example.klaudia.help;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Klaudia on 2015-01-17.
 */

public class Medyczne_info extends Activity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_medyczne_info);

            SharedPreferences sharedPreferences = getSharedPreferences("com.example.klaudia.help", Context.MODE_PRIVATE);

            String grupa= sharedPreferences.getString("grupa_krwi", "brak uzupełnienia");
            String choroby= sharedPreferences.getString("choroby_zakazne", "brak uzupełnienia");
            String leki=sharedPreferences.getString("aktualne_leki", "brak uzupełnienia");


            TextView tekst=(TextView)findViewById(R.id.Grupa_krwi);
            tekst.setText(grupa);

            TextView tekst2=(TextView)findViewById(R.id.Choroby);
            tekst2.setText(choroby);

            TextView tekst3=(TextView)findViewById(R.id.Leki);
            tekst3.setText(leki);




        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.medyczne_info, menu);
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
                Intent intent = new Intent(getApplicationContext(), Setings.class);
                startActivity(intent);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }


