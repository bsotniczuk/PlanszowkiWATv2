package com.example.bartomiej.planszowkiwatv2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EkranLogowania extends AppCompatActivity {

    //0 -> niezalogowany | 1 -> zalogowany
    static int loggedInState = 0;

    EditText loginEditText;
    EditText passwordEditText;

    DatabaseHelper mDatabaseHelper;
    DatabaseHelperGames mDatabaseHelperGames;

    //user Data
    static String idOfUser = "-1"; //-1 to uzytkownik nie zalogowany
    static String userSkipped = "-1";
    static String admin = "2";
    static String isLoggedInfo = "1";

    static String currentUserLogin="Uzytkownik nie zalogowany";
    static String currentUserID = "-1";

    static List listOfBorrowedGames;

    //game Data
    static String gameNotBorrowedID = "0"; //0 is a state when game isn't borrowed by anyone

    String isLogged = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_logowania);

        init();

        listOfBorrowedGames = new ArrayList();

        //if user is logged in continue to MainActivity
        if (isLogged.compareTo("true") == 0) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    public void login(View view) {
        //Toast.makeText(getApplicationContext(), "login pressed", Toast.LENGTH_SHORT);

        String login = loginEditText.getText().toString();
        String haslo = passwordEditText.getText().toString();

        Cursor abc = mDatabaseHelper.getIfExists(login);

        if (login.compareTo("false") == 0 || login.compareTo("true") == 0) {
            Toast.makeText(getApplicationContext(), "Cannot login as true or false", Toast.LENGTH_SHORT).show();
        }
        //uzytkownik istnieje w bazie danych
        else if (abc.moveToFirst()) {
            String c = abc.getString(2);

            int check = haslo.compareTo(c); //informacyjna dana

            //check password
            if (haslo.compareTo(c) == 0) {
                idOfUser = abc.getString(0);
                currentUserLogin = abc.getString(1);
//                isLogged = "true";

                int temp = mDatabaseHelper.updateUserInfo("1", "true", abc.getString(0));

                if (!MainActivity.HideToast) Toast.makeText(getApplicationContext(), "Uzytkownik moze poprawnie sie zalogowac | " + check + " | id: " + abc.getString(0) + " | UPDATE: " + temp, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Podano błędne hasło", Toast.LENGTH_SHORT).show();
            }
        } else {
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

    public void init() {
        try {
            mDatabaseHelper = new DatabaseHelper(this);

            String userInfoLogin = "false"; //czy zalogowany | login
            String userInfoPassword = userSkipped; //id uzytkownika zalogowanego | password

            String userAdminLogin = "admin";
            String userAdminPassword = "admin";

            String user1Login = "Bartlomiej";
            String user1Password = "abc";

            String user2Login = "StasiakA";
            String user2Password = "StasiakA";

            //populate Database
            if (!mDatabaseHelper.checkIfExists(userInfoLogin))
                mDatabaseHelper.addData(1, userInfoLogin, userInfoPassword);
            if (!mDatabaseHelper.checkIfExists(userAdminLogin))
                mDatabaseHelper.addData(2, userAdminLogin, userAdminPassword);
            if (!mDatabaseHelper.checkIfExists(user1Login))
                mDatabaseHelper.addData(3, user1Login, user1Password);
            if (!mDatabaseHelper.checkIfExists(user2Login))
                mDatabaseHelper.addData(4, user2Login, user2Password);

            Cursor data = mDatabaseHelper.getData();

            if (data.moveToFirst()) {
                //Toast.makeText(getApplicationContext(), "Baza Danych zawiera elementy", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Baza Danych jest pusta", Toast.LENGTH_SHORT).show();
            }

            //sprawdz informacje o użytkowniku zalogowanym
            String checkString = userInfoLogin;
            Cursor abc = mDatabaseHelper.getIfExists(1);
            if (abc.moveToFirst()) {
                String a = abc.getString(0);
                String b = abc.getString(1);
                String c = abc.getString(2);

                currentUserID = a;
                isLogged = b;
                idOfUser = c;

                //oznajmianie całej aplikacji o loginie zalogowanej osoby
                //dodaj opcje sprawdzania czy hasła się zgadzają, żeby zapobiec włamania na czyjeś konto
                //edytując pamięć urządzenia i zmieniając niskopoziomowo ID
                //dodaj opcję dwóch baz danych w których będziesz trzymał informację lokalnie i serwerowo
                //lokalna baza danych będzie trzymać tylko informację o aktualnie zalogowanym użytkowniku
                int currentUserIDint = Integer.parseInt(c.trim());

                Cursor currentUserInfo = mDatabaseHelper.getIfExists(currentUserIDint);
                if (currentUserInfo.moveToFirst())
                {
                    currentUserLogin = currentUserInfo.getString(1);
                }

                String log = "BD zawiera uzytkownika (" + checkString + ") o id: "
                        + a + " | loginie: " + b + " | haśle: " + c;

                Log.i("WATlog", log);
                //Toast.makeText(getApplicationContext(), log, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "Exception: " + e, Toast.LENGTH_LONG).show();
            Log.i("WATlog", "Exception: " + e);
        }
    }

    public void initGames() {
        try {
            mDatabaseHelperGames = new DatabaseHelperGames(this);

            /*
            ImageButton a5sekund;
    ImageButton b7cudow;
    ImageButton c3catan;
    ImageButton d4doble;
    ImageButton e5jungleSpeed;
             */
            String gameNotBorrowedID = "0";

            String game5sekund = "5 Sekund"; //czy zalogowany | login

            String game7cudow = "7 Cudow";

            String gameCatan = "Catan";

            String gameDobble = "Dobble";

            String gameJungleSpeed = "Jungle Speed";

            //populate Database
            if (!mDatabaseHelperGames.checkIfExists(game5sekund))
                mDatabaseHelperGames.addData(1, game5sekund, gameNotBorrowedID);
            if (!mDatabaseHelperGames.checkIfExists(game7cudow))
                mDatabaseHelperGames.addData(2, game7cudow, gameNotBorrowedID);
            if (!mDatabaseHelperGames.checkIfExists(gameCatan))
                mDatabaseHelperGames.addData(3, gameCatan, gameNotBorrowedID);
            if (!mDatabaseHelperGames.checkIfExists(gameDobble))
                mDatabaseHelperGames.addData(4, gameDobble, gameNotBorrowedID);
            if (!mDatabaseHelperGames.checkIfExists(gameJungleSpeed))
                mDatabaseHelperGames.addData(5, gameJungleSpeed, gameNotBorrowedID);

            Cursor data = mDatabaseHelperGames.getData();

            if (data.moveToFirst()) {
                //Toast.makeText(getApplicationContext(), "Baza Danych zawiera elementy", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Baza Danych jest pusta", Toast.LENGTH_SHORT).show();
            }

            Cursor borrowedGames = mDatabaseHelperGames.getAllBorrowed();

            if(borrowedGames.moveToFirst())
            {
                listOfBorrowedGames.add(borrowedGames); //add current Cursor to the list of BorrowedGames

                while(borrowedGames.moveToNext()) //iterate till the last game in Database
                {
                    listOfBorrowedGames.add(borrowedGames);
                }
            }

            //sprawdz informacje o użytkowniku zalogowanym
            //String checkString = userInfoLogin;
            /*Cursor abc = mDatabaseHelper.getIfExists(1);
            if (abc.moveToFirst()) {
                String a = abc.getString(0);
                String b = abc.getString(1);
                String c = abc.getString(2);

                currentUserID = a;
                isLogged = b;
                idOfUser = c;

                //oznajmianie całej aplikacji o loginie zalogowanej osoby
                //dodaj opcje sprawdzania czy hasła się zgadzają, żeby zapobiec włamania na czyjeś konto
                //edytując pamięć urządzenia i zmieniając niskopoziomowo ID
                //dodaj opcję dwóch baz danych w których będziesz trzymał informację lokalnie i serwerowo
                //lokalna baza danych będzie trzymać tylko informację o aktualnie zalogowanym użytkowniku
                int currentUserIDint = Integer.parseInt(c.trim());

                Cursor currentUserInfo = mDatabaseHelper.getIfExists(currentUserIDint);
                if (currentUserInfo.moveToFirst())
                {
                    currentUserLogin = currentUserInfo.getString(1);
                }

                String log = "BD zawiera grę (" + ") o id: "
                        + a + " | loginie: " + b + " | haśle: " + c;

                Log.i("WATlog", log);
                Toast.makeText(getApplicationContext(), log, Toast.LENGTH_LONG).show();
            }*/
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "Exception: " + e, Toast.LENGTH_LONG).show();
            Log.i("WATlog", "Exception: " + e);
        }
    }

}
