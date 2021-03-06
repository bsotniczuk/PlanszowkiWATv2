package com.example.bartomiej.planszowkiwatv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class EventInfo extends AppCompatActivity {

    ImageView eventInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eventInfo = findViewById(R.id.eventInfo);

        if (Wydarzenia.whichEvent == 1){
            eventInfo.setImageResource(R.drawable.a1eventjpg);
        }
        else if (Wydarzenia.whichEvent == 2)
        {
            eventInfo.setImageDrawable(null);
            eventInfo.setImageResource(R.drawable.b2eventjpg);
        }
        else if (Wydarzenia.whichEvent == 3)
        {
            eventInfo.setImageResource(R.drawable.c3eventjpg);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Wydarzenia.class);
        startActivity(intent);
        finish();
    }
}
