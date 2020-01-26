package com.example.bartomiej.planszowkiwatv2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ListaGier extends AppCompatActivity {

    static int whichGame = 1;
    public boolean sortIterator = false;

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
    protected void onDestroy() {
        super.onDestroy();

        //clear the image resource, to improve memory efficiency
        a5sekund.setImageResource(android.R.color.transparent);
        b7cudow.setImageResource(android.R.color.transparent);
        c3catan.setImageResource(android.R.color.transparent);
        d4doble.setImageResource(android.R.color.transparent);
        e5jungleSpeed.setImageResource(android.R.color.transparent);
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
            //ShowToast("Refresh pressed");
            finish();
            startActivity(getIntent());

            return true;
        } else if (id == R.id.action_sort) {
            //ShowToast("Sort pressed");
            sort();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sort() {
        if (!sortIterator) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) e5jungleSpeed.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, R.id.dummy);
            params.bottomMargin = 0;

            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) d4doble.getLayoutParams();
            params1.addRule(RelativeLayout.BELOW, R.id.e5jungleSpeed);

            RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) c3catan.getLayoutParams();
            params2.addRule(RelativeLayout.BELOW, R.id.d4doble);

            RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) b7cudow.getLayoutParams();
            params3.addRule(RelativeLayout.BELOW, R.id.c3catan);

            RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams) a5sekund.getLayoutParams();
            params4.addRule(RelativeLayout.BELOW, R.id.b7cudow);
            params4.bottomMargin = (int) dpToPx(60f);

            e5jungleSpeed.setLayoutParams(params);
            d4doble.setLayoutParams(params1);
            c3catan.setLayoutParams(params2);
            b7cudow.setLayoutParams(params3);
            a5sekund.setLayoutParams(params4);

            sortIterator = true;
        }
        else if (sortIterator){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) e5jungleSpeed.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, R.id.d4doble);
            params.bottomMargin = (int) dpToPx(60f);

            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) d4doble.getLayoutParams();
            params1.addRule(RelativeLayout.BELOW, R.id.c3catan);

            RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) c3catan.getLayoutParams();
            params2.addRule(RelativeLayout.BELOW, R.id.b7cudow);

            RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) b7cudow.getLayoutParams();
            params3.addRule(RelativeLayout.BELOW, R.id.a5sekund);

            RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams) a5sekund.getLayoutParams();
            params4.addRule(RelativeLayout.BELOW, R.id.dummy);
            params4.bottomMargin = 0;

            a5sekund.setLayoutParams(params4);
            b7cudow.setLayoutParams(params3);
            c3catan.setLayoutParams(params2);
            d4doble.setLayoutParams(params1);
            e5jungleSpeed.setLayoutParams(params);

            sortIterator = false;
        }
    }

    public void showInfo(View view) {
        //view.get
        int viewId = view.getId();

        if (viewId == a5sekund.getId()) {
            if (!MainActivity.HideToast) ShowToast("a5sekundPressed");

            whichGame = 1;
            Intent intent = new Intent(getApplicationContext(), GameInfo.class);
            startActivity(intent);
        } else if (viewId == b7cudow.getId()) {
            if (!MainActivity.HideToast) ShowToast("b7cudowPressed");

            whichGame = 2;
            Intent intent = new Intent(getApplicationContext(), GameInfo.class);
            startActivity(intent);
        } else if (viewId == c3catan.getId()) {
            if (!MainActivity.HideToast) ShowToast("c3catanPressed");

            whichGame = 3;
            Intent intent = new Intent(getApplicationContext(), GameInfo.class);
            startActivity(intent);
        } else if (viewId == d4doble.getId()) {
            if (!MainActivity.HideToast) ShowToast("d4doblePressed");

            whichGame = 4;
            Intent intent = new Intent(getApplicationContext(), GameInfo.class);
            startActivity(intent);
        } else if (viewId == e5jungleSpeed.getId()) {
            if (!MainActivity.HideToast) ShowToast("e5jungleSpeedPressed");

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

    public float dpToPx(float dip) {
        // Converts 14 dip into its equivalent px
        //float dip = 14f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        return px;
    }

}
