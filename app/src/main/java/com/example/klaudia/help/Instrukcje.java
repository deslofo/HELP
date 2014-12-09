package com.example.klaudia.help;



import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Instrukcje extends ActionBarActivity {



    public void reanimacja (View view){
        Log.d("Instrukcje", "Weszlo w reanimacje");

    }
    public void oparzenie(View view){

    }
    public void omdlenie(View view){

    }
    public void złamanie(View view){

    }
    public void skaleczenie(View view){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Instrukcje","onCreate");
        setContentView(R.layout.wybor_instrukcji);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d("Instrukcje"," przed onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.instrukcje, menu);
        Log.d("Instrukcje"," po onCreateOptionsMenu");
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
