package com.example.bartomiej.planszowkiwatv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    Button logOutButton;
    Button myReservationButton;

    Switch switch1;

    TextView currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switch1 = findViewById(R.id.switch1);

        logOutButton = findViewById(R.id.logOutButton);
        myReservationButton = findViewById(R.id.myReservationButton);

        currentUser = findViewById(R.id.currentUserLogin);

        currentUser.setText(EkranLogowania.currentUserLogin);

        if (RegisterScreen.isNotifications) switch1.setChecked(true);
        else switch1.setChecked(false);

        //if user skipped, display different String
        if (EkranLogowania.idOfUser.compareTo(EkranLogowania.userSkipped) == 0) {
            logOutButton.setText("Wróć do ekranu logowania");
            myReservationButton.setVisibility(View.INVISIBLE);
        }
        else {
            logOutButton.setText("Wyloguj się");
            myReservationButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void notifications(View view){
        if(switch1.isChecked()) RegisterScreen.isNotifications = true;
        else RegisterScreen.isNotifications = false;

        Toast.makeText(getApplicationContext(), "Notifications set to: " + switch1.isChecked(), Toast.LENGTH_SHORT).show();
    }

    public void logOut(View view){

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        //save in DB that user logged out
        int temp = mDatabaseHelper.updateUserInfo("1", "false", EkranLogowania.userSkipped);
        EkranLogowania.currentUserLogin = "Uzytkownik nie zalogowany";
        currentUser.setText(EkranLogowania.currentUserLogin);
        Toast.makeText(getApplicationContext(), "Uzytkownik poprawnie wylogowany | UPDATE: " + temp, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), EkranLogowania.class);
        startActivity(intent);
        finish();
    }

    public void checkReservation(View view){
        Toast.makeText(getApplicationContext(), "Moje rezerwacje", Toast.LENGTH_SHORT).show();
    }

}
