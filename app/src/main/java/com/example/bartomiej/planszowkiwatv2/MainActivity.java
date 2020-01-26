package com.example.bartomiej.planszowkiwatv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static boolean HideToast = true; //hide Toast needed to debug app in Real Time

    static boolean notifications;
    ImageButton listaGier;
    ImageButton listaWydarzen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaGier = findViewById(R.id.listaGier);
        listaWydarzen = findViewById(R.id.wydarzenia);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //clear the image resource, to improve memory efficiency
        listaGier.setImageResource(android.R.color.transparent);
        listaWydarzen.setImageResource(android.R.color.transparent);
    }

    @Override
    public void onBackPressed() {
        //do nothing because I don't want to allow user to get into a login screen
        // Do Here what ever you want do on back press;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            //ShowToast("Settings pressed");
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void listaGier(View view){
        //ShowToast("Lista Gier Opened");
        Intent intent = new Intent(getApplicationContext(), ListaGier.class);
        startActivity(intent);
        finish();
    }

    public void wydarzenia(View view){
        //ShowToast("Wydarzenia Opened");
        Intent intent = new Intent(getApplicationContext(), Wydarzenia.class);
        startActivity(intent);
        finish();
    }

    public void ShowToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
