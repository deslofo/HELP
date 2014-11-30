package com.example.klaudia.help;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Bliscy extends ActionBarActivity {


    public void zawiadom(View view){
        Intent intent;
        String telefon;
        switch (view.getId()){
            case R.id.sylwia:
                intent = new Intent(getApplicationContext(), SMS.class);
                telefon = "881204283";

                intent.putExtra("telefon", telefon);
                startActivity(intent);
                return;

            case R.id.magda:
                intent = new Intent(getApplicationContext(), SMS.class);
                telefon = "796558784";
                intent.putExtra("telefon", telefon);
                startActivity(intent);
                return;

            case R.id.wojtek:
                intent = new Intent(getApplicationContext(), SMS.class);
                telefon = "608090497";
                intent.putExtra("telefon", telefon);
                startActivity(intent);
                return;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bliscy);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bliscy, menu);
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
}
