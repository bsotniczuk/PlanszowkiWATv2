package com.example.bartomiej.planszowkiwatv2;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GameInfo extends AppCompatActivity {

    ImageView a5;
    ImageView b7;
    ImageView c3;
    ImageView d4;
    ImageView e5;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        a5 = findViewById(R.id.a5);

        if (ListaGier.whichGame == 1){
            a5.setImageResource(R.drawable.a1jpg);
        }
        else if (ListaGier.whichGame == 2)
        {
            a5.setImageDrawable(null);
            a5.setImageResource(R.drawable.b2jpg);
        }
        else if (ListaGier.whichGame == 3)
        {
            a5.setImageResource(R.drawable.c3jpg);
        }
        else if (ListaGier.whichGame == 4)
        {
            a5.setImageResource(R.drawable.d4jpg);
        }
        else if (ListaGier.whichGame == 5)
        {
            //e5.setVisibility(View.VISIBLE);
            a5.setImageResource(R.drawable.e5jpg);
        }
    }
}
