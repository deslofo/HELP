package com.example.klaudia.help;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Zadwon extends ActionBarActivity {
    public void policja(View view){
        dzwon("881204283");
    }

    public void pogotowie(View view){
        dzwon("881204283");
    }

    public void straz(View view){
        dzwon("881204283");
    }

    public void sto(View view){
        dzwon("881204283");
    }
    public void dzwon(String tel){
        Uri number = Uri.parse("tel:" + tel);
        Intent dial = new Intent(Intent.ACTION_CALL, number);
        startActivity(dial);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadwon);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.zadwon, menu);
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
