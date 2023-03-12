package com.example.fillcolorandcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView btnBlack;
    TextView btnBlue;
    TextView btnGreen;
    TextView btnRed;
    TextView btnCount;
    static TextView screenCor;
    public static final String SHARE_PRES = "com.example.fillcolorandcount";
   /* public static final String count = "count";
    public static final String COLOR = "kiet";*/

    private String getcount;
    private String getColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBlack = findViewById(R.id.btnblack);
        btnBlue = findViewById(R.id.btnblue);
        btnCount = findViewById(R.id.btncount);
        btnGreen = findViewById(R.id.btngreen);
        btnRed = findViewById(R.id.btnred);
        screenCor = findViewById(R.id.screen);

        btnBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCor.setBackgroundColor(Color.BLACK);
            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCor.setBackgroundColor(Color.RED);
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCor.setBackgroundColor(Color.BLUE);
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCor.setBackgroundColor(Color.GREEN);
            }
        });
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCor.setText(String.valueOf(Integer.valueOf(screenCor.getText().toString()) + 1));
                /*  SaveData();*/
            }
        });
        /*loadData();
        setview();*/
        PreferenceManager.setDefaultValues(this,R.xml.root_preferences,false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        getcount = preferences.getString("kiettuan", "0");
        getColor = preferences.getString("default_color", "red");
        screenCor.setText(String.valueOf(getcount));
        setColor(getColor);




    }
    @Override
    public void onBackPressed() {
        // Get the user's selections
        //   int defaultCount = Integer.parseInt(screenCor.getText().toString());
        //  String defaultColor = (String)  ;

        // Save the selections to the preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("kiettuan", getcount);
        editor.putString("default_color",getColor);

        editor.apply();

        // Return to the previous activity
        super.onBackPressed();
    }
    public static void setCount(String count) {
        screenCor.setText(count);
    }
    public static void setColor(String color) {
        switch (color) {
            case "black":
                screenCor.setBackgroundColor(Color.BLACK);
                break;
            case "red":
                screenCor.setBackgroundColor(Color.RED);
                break;
            case "green":
                screenCor.setBackgroundColor(Color.GREEN);
                break;
            case "blue":
                screenCor.setBackgroundColor(Color.BLUE);
                break;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("kiettuan", screenCor.getText().toString());
        editor.putString("default_color", getColor);
        editor.apply();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
   /* public void SaveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PRES,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(count,screenCor.getText().toString());
        ColorDrawable backgroundColor = (ColorDrawable) screenCor.getBackground();
        int color = backgroundColor.getColor();
        editor.putInt(COLOR, color);
        editor.apply();
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PRES,MODE_PRIVATE);
        getcount = sharedPreferences.getString(count,"0");
        getColor = sharedPreferences.getInt(COLOR,0);
    }
    public void setview(){
        screenCor.setText(getcount);
        screenCor.setBackgroundColor(getColor);
    }*/
}