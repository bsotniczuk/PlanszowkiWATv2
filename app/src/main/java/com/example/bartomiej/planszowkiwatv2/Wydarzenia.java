package com.example.bartomiej.planszowkiwatv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Wydarzenia extends AppCompatActivity {

    static int whichEvent=1;

    ImageButton a1wydarzenie;
    ImageButton b2wydarzenie;
    ImageButton c3wydarzenie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wydarzenia);

        a1wydarzenie = findViewById(R.id.a1wydarzenie);
        b2wydarzenie = findViewById(R.id.b2wydarzenie);
        c3wydarzenie = findViewById(R.id.c3wydarzenie);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void showEventInfo(View view){
        //view.get
        int viewId = view.getId();

        if (viewId == a1wydarzenie.getId())
        {
            ShowToast("a1");

            whichEvent = 1;
            Intent intent = new Intent(getApplicationContext(), EventInfo.class);
            startActivity(intent);
        }
        else if (viewId == b2wydarzenie.getId())
        {
            ShowToast("b2");

            whichEvent = 2;
            Intent intent = new Intent(getApplicationContext(), EventInfo.class);
            startActivity(intent);
        }
        else if (viewId == c3wydarzenie.getId())
        {
            ShowToast("c3");

            whichEvent = 3;
            Intent intent = new Intent(getApplicationContext(), EventInfo.class);
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
