package com.example.bartomiej.planszowkiwatv2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterScreen extends AppCompatActivity {

    String log="";

    EditText login;
    EditText password;
    EditText email;
    EditText name;
    EditText surname;

    Switch notifications;

    TextView loginText;
    TextView passwordText;
//    TextView emailText;
    TextView nameText;
    TextView surnameText;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        login = findViewById(R.id.loginEditTextRegister);
        password = findViewById(R.id.passwordEditTextRegister);
        email = findViewById(R.id.emailEditTextRegister);
        name = findViewById(R.id.nameEditTextRegister);
        surname = findViewById(R.id.surnameEditTextRegister);

        notifications = findViewById(R.id.notificationsSwitchRegister);

        loginText = findViewById(R.id.loginTextRegister);
        passwordText = findViewById(R.id.passwordTextRegister);
//        emailText = findViewById(R.id.emailText);
        nameText = findViewById(R.id.nameText);
        surnameText = findViewById(R.id.surnameText);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), EkranLogowania.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            //Toast.makeText(getApplicationContext(), "Done pressed", Toast.LENGTH_SHORT).show();

            //if register process completed without errors
            if (registerProcess()){

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{

            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean registerProcess(){

        boolean errors=false;

        log = login.getText().toString();
        String pass = password.getText().toString();
        String ema = email.getText().toString();
        String nam = name.getText().toString();
        String surn = surname.getText().toString();

        if (log.compareTo("") == 0){
            //Toast.makeText(getApplicationContext(), "name is null", Toast.LENGTH_SHORT).show();
            loginText.setText("Login (Pole jest wymagane)");
            loginText.setTextColor(Color.RED);

            errors = true;
        }
        if (pass.compareTo("") == 0){
            //Toast.makeText(getApplicationContext(), "name is null", Toast.LENGTH_SHORT).show();
            passwordText.setText("Hasło (Pole jest wymagane)");
            passwordText.setTextColor(Color.RED);

            errors = true;
        }
        if (nam.compareTo("") == 0){
            //Toast.makeText(getApplicationContext(), "name is null", Toast.LENGTH_SHORT).show();
            nameText.setText("*  Imie (Pole jest wymagane)");
            nameText.setTextColor(Color.RED);

            errors = true;
        }
        if (surn.compareTo("") == 0){
            //Toast.makeText(getApplicationContext(), "surname is null", Toast.LENGTH_SHORT).show();
            surnameText.setText("*  Nazwisko (Pole jest wymagane)");
            surnameText.setTextColor(Color.RED);

            errors = true;
        }

        if (errors == false) {
            mDatabaseHelper = new DatabaseHelper(this);

            Cursor data = mDatabaseHelper.getIfExists(log);

            //if user with the same exact login doesnt exist in database, then allow to signup
            if(data.moveToFirst()== false) {

                mDatabaseHelper.addData(log, pass);

                data = mDatabaseHelper.getIfExists(log);

                if (data.moveToFirst()) {
                    EkranLogowania.idOfUser = data.getString(0);

                    Toast.makeText(getApplicationContext(), "User: " + data.getString(1) + " | with id: " + data.getString(0) + " added", Toast.LENGTH_SHORT).show();
                }

                resetAllErrors();

                return true;
            }
            else{
                loginText.setText("Login (Użytkownik już istnieje)");
                loginText.setTextColor(Color.RED);

                return false;
            }
        }
        else return false;
    }

    public void resetAllErrors(){
        loginText.setText("Login");
        loginText.setTextColor(Color.WHITE);

        passwordText.setText("Hasło");
        passwordText.setTextColor(Color.WHITE);

        nameText.setText("*  Imie");
        nameText.setTextColor(Color.WHITE);

        surnameText.setText("*  Nazwisko");
        surnameText.setTextColor(Color.WHITE);
    }
}
