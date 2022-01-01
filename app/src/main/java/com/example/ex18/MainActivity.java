package com.example.ex18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /**
     * @author        Etay Sabag
     * @version       1.0
     * @since         31/12/2021
     *
     * MainActivity class, the main screen.
     * The class contains buttons that do different things.
     */
    int counter = 0;
    TextView tV;
    EditText eT;
    Intent si;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);


        tV = (TextView) findViewById(R.id.textView);
        eT = (EditText) findViewById(R.id.username);
    }

    @Override
    protected void onResume(){
        /**
         * The method will occur when the app is loaded.
         * The method will load all the data that was saved in the app back.
         * @param view
         */
        super.onResume();
        counter = settings.getInt("countValue",0);
        tV.setText(Integer.toString(counter));
        eT.setText(settings.getString("userName",""));
    }
    @Override
    protected void onPause() {
        /**
         * The method will occur when the app is closed or paused.
         * The method will save all the data the user changed.
         * @param view
         */
        super.onPause();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("countValue", counter);
        editor.putString("userName", eT.getText().toString());
        editor.commit();
    }


    public void count(View view) {
        /**
         * The method add one to the counter and displays the change.
         *
         * @param view
         */
        counter = counter+1;
        tV.setText(Integer.toString(counter));
    }

    public void reset(View view) {
        /**
         * The method resets the counter and displays the change.
         *
         * @param view
         */
        counter = 0;
        tV.setText(Integer.toString(counter));
    }


    public void exit(View view) {
        /**
         * The method exits the app.
         *
         * @param view
         */
        finish();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuCredits){
            startActivityForResult(si, 1);
        }
        return true;
    }
}