package com.example.bartomiej.planszowkiwatv2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EkranLogowania extends AppCompatActivity {

    //0 -> niezalogowany | 1 -> zalogowany
    static int loggedInState = 0;

    EditText loginEditText;
    EditText passwordEditText;

    DatabaseHelper mDatabaseHelper;

    static String idOfUser = "-1"; //-1 to uzytkownik nie zalogowany
    static String userSkipped = "-1";
    static String admin = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_logowania);

        mDatabaseHelper = new DatabaseHelper(this);

//        mDatabaseHelper.addData("admin", "admin");
//        mDatabaseHelper.addData("Bartlomiej", "abc");
//        mDatabaseHelper.addData("Stasiak", "Stasiak");

        Cursor data = mDatabaseHelper.getData();

        if (data.moveToFirst()) {
            //Toast.makeText(getApplicationContext(), "Baza Danych zawiera elementy", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Baza Danych jest pusta", Toast.LENGTH_SHORT).show();
        }

        //mDatabaseHelper.deleteAllData();

        String checkString = "admin";
        Cursor abc = mDatabaseHelper.getIfExists(checkString);
        if (abc.moveToFirst()) {
            String a = abc.getString(0);
            String b = abc.getString(1);
            String c = abc.getString(2);

            Toast.makeText(getApplicationContext(), "BD zawiera uzytkownika " + checkString + " o id: "
                    + a + " | loginie: " + b + " | haśle: " + c, Toast.LENGTH_LONG).show();
        }

        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    public void login(View view) {
        //Toast.makeText(getApplicationContext(), "login pressed", Toast.LENGTH_SHORT);

        String login = loginEditText.getText().toString();
        String haslo = passwordEditText.getText().toString();

        Cursor abc = mDatabaseHelper.getIfExists(login);

        //uzytkownik istnieje w bazie danych
        if (abc.moveToFirst()) {
            String c = abc.getString(2);

            int check = haslo.compareTo(c);

            //check password
            if (haslo.compareTo(c) == 0)
            {
                idOfUser = abc.getString(0);
                Toast.makeText(getApplicationContext(), "Uzytkownik moze poprawnie sie zalogowac | " + check + " | id: " + idOfUser, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Podano błędne hasło", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Podany uzytkownik nie istnieje", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Toast.makeText(getApplicationContext(), "register pressed", Toast.LENGTH_SHORT);

        Intent intent = new Intent(getApplicationContext(), RegisterScreen.class);
        startActivity(intent);
        finish();
    }

    public void skip(View view) {
        Toast.makeText(getApplicationContext(), "skip pressed", Toast.LENGTH_SHORT);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}
