package com.example.bartomiej.planszowkiwatv2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switch1 = findViewById(R.id.switch1);
    }

    public void notifications(View view){
        if(switch1.isChecked()) MainActivity.notifications = true;
        else MainActivity.notifications = false;

        Toast.makeText(getApplicationContext(), "Notifications set to: " + MainActivity.notifications, Toast.LENGTH_SHORT).show();
    }

}
