package com.example.bartomiej.planszowkiwatv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class EkranLogowania extends AppCompatActivity {

    //0 -> niezalogowany | 1 -> zalogowany
    static int loggedInState=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_logowania);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
    }

    public void login(View view){
        Toast.makeText(getApplicationContext(), "login pressed", Toast.LENGTH_SHORT);

        /*if (loggedIn)
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);*/
    }

    public void register(View view){
        Toast.makeText(getApplicationContext(), "register pressed", Toast.LENGTH_SHORT);

        Intent intent = new Intent(getApplicationContext(), RegisterScreen.class);
        startActivity(intent);
        finish();
    }

    public void skip(View view){
        Toast.makeText(getApplicationContext(), "skip pressed", Toast.LENGTH_SHORT);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}
