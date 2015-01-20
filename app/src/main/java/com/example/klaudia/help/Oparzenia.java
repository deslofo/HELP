package com.example.klaudia.help;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class Oparzenia extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oparzenia);

        WebView webView = (WebView) findViewById(R.id.info_oparzenia);
        String text = "<html>" +
                "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />" +
                "<body style=\"text-align:justify;color:black;;\"> <ol>\n" +
                "<li>Upewnij się że nic ci nie zagraża.</li>" +
                "<li>Jeśli ubranie się pali lub tli - ugaś je. Do tego celu NIE używaj gaśnicy.</li>" +
                "<li>Wezwij pogotowie jeśli oparzenie jest rozlegle.</li>" +
                "<li>Zdejmij ubranie. Jeżeli ubranie jest wtopione w ciało to nie zrywaj go tylko wytnij ubranie wokół rany.</li>" +
                "<li>Jeżeli oparzona jest ręka ściągnij biżuterie - zanim narastający obrzęk uniemożliwi to.</li>" +
                "<li>Ochładzaj oparzona część ciała czystą, chłodną wodą od 10 do 20 minut.</li>" +
                "<li>Po ochłodzeniu poczekaj, aż oparzona skóra wyschnie, następnie osłoń opatrunkiem oparzeniowym lub mokrym opatrunekiem.</li>" +
                "</ol>" +
                "<p style=\"text-align:center;\">" +
                "<strong>Jakich błędów unikać przy udzielaniu pomocy osobom poparzonym:</strong>" +
                "</p>" +
                "<ol>" +
                "<li>Nie wolno podawać niczego doustnie.</li>" +
                "<li>Nie wolno smarować oparzonej skóry maściami, kremami, tłuszczami.</li>" +
                "<li>Nie wolno przekuwać pęcherzy.</li>" +
                "<li>Nie wolno smarować alkoholem.</li>" +
                "<li>Nie wolno zostawiać ratowanego samego, jeśli oparzenie jest rozlegle.</li>" +
                "</ol>" +
                "</body></Html>";

        webView.loadData(text, "text/html; charset=utf-8", "utf-8");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.oparzenia, menu);
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
}
