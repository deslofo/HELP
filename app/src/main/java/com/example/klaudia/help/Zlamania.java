package com.example.klaudia.help;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class Zlamania extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zlamania);

        WebView webView = (WebView) findViewById(R.id.info_zlamania);
        String text = "<html>" +
                "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />" +
                "<body style=\"text-align:justify;color:black;;\"> <ol>\n" +
                "<li>Jeżeli wystąpiło otwarte złamanie, nałożyć jałowy opatrunek, bez zmiany położenia przemieszczonych kości, lekko obandażować.</li>" +
                "<li>Przy złamaniu zamkniętym nie należy ściągać odzieży z miejsca złamania, przy otwartym - naciąć odzież wzdłuż szwów, by opatrzyć ranę.</li>" +
                "<li>Niedopuszczalne jest nastawianie kończyny we własnym zakresie!</li>" +
                "<li>Zabezpieczyć poszkodowanego przed dalszymi, ewentualnymi urazami i utratą ciepła.</li>" +
                "<li>Można stosować zimne okłady (obłożyć bolesne miejsca lodem w woreczku).</li>" +
                "<li>Jak najszybciej zapewnić poszkodowanemu fachową pomoc medyczną.</li>" +
                "<li>Doraźne unieruchamianie złamanych kończyn powinno obejmować co najmniej dwa sąsiadujące stawy.</li>" +
                "</ol></body></Html>";

        webView.loadData(text, "text/html; charset=utf-8", "utf-8");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.zlamania, menu);
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
