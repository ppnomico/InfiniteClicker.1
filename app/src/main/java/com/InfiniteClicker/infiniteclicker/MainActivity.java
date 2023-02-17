package com.InfiniteClicker.infiniteclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    int defaultColour;
    Button clickMeButton;
    ImageView colourImageButton, settingsButton;
    TextView clickCountView;
    public int clickCounter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickMeButton = (Button) findViewById(R.id.clickMeButton);
        clickCountView = (TextView) findViewById(R.id.clickCountView);
        colourImageButton = (ImageView) findViewById(R.id.colourImageButton);
        settingsButton = (ImageView) findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });

        defaultColour = ContextCompat.getColor(MainActivity.this, R.color.purple_200);

        colourImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openColourPicker();
            }
                                             }

        );

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);

        clickMeButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            clickMeButton.startAnimation(animation);
            clickCounter++;
            clickCountView.setText("" + clickCounter);
                }
            }
        );
    }

    private void openColourPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColour, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColour = color;
                clickMeButton.setBackgroundColor(color);
            }
        });
        ambilWarnaDialog.show();
    }

    private void openSettings() {
        Intent settingsPage = new Intent(MainActivity.this, SettingsPage.class);
        startActivity(settingsPage);
    }

    //    uses shared preferences to store click count when user exits or enters the application
    public void onResume(){
        super.onResume();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        int a = sh.getInt("clickCounter", clickCounter);

        clickCountView.setText("" + a);
        clickCounter = a;
    }
//    @Override
    public void onPause(){
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putInt("clickCounter", clickCounter);
        myEdit.apply();
    }


}