package com.example.bartomiej.planszowkiwatv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ListaGier extends AppCompatActivity {

    static int whichGame=1;

    ImageButton a5sekund;
    ImageButton b7cudow;
    ImageButton c3catan;
    ImageButton d4doble;
    ImageButton e5jungleSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gier);

        Toolbar toolbar = findViewById(R.id.toolbar);

        a5sekund = findViewById(R.id.a5sekund);
        b7cudow = findViewById(R.id.b7cudow);
        c3catan = findViewById(R.id.c3catan);
        d4doble = findViewById(R.id.d4doble);
        e5jungleSpeed = findViewById(R.id.e5jungleSpeed);

        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_gier, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            ShowToast("Refresh pressed");
            return true;
        }
        else if(id == R.id.action_sort){
            ShowToast("Sort pressed");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showInfo(View view){
        //view.get
        int viewId = view.getId();

        if (viewId == a5sekund.getId())
        {
            ShowToast("a5sekundPressed");

            whichGame = 1;
            Intent intent = new Intent(getApplicationContext(), GameInfo.class);
            startActivity(intent);
        }
        else if (viewId == b7cudow.getId())
        {
            ShowToast("b7cudowPressed");

            whichGame = 2;
            Intent intent = new Intent(getApplicationContext(), GameInfo.class);
            startActivity(intent);
        }
        else if (viewId == c3catan.getId())
        {
            ShowToast("c3catanPressed");

            whichGame = 3;
            Intent intent = new Intent(getApplicationContext(), GameInfo.class);
            startActivity(intent);
        }
        else if (viewId == d4doble.getId())
        {
            ShowToast("d4doblePressed");

            whichGame = 4;
            Intent intent = new Intent(getApplicationContext(), GameInfo.class);
            startActivity(intent);
        }
        else if (viewId == e5jungleSpeed.getId())
        {
            ShowToast("e5jungleSpeedPressed");

            whichGame = 5;
            Intent intent = new Intent(getApplicationContext(), GameInfo.class);
            startActivity(intent);
        }
    }

    public void ShowToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
