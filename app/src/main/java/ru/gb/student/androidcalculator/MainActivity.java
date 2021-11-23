package ru.gb.student.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import ru.gb.student.androidcalculator.logic.CalcLogicVisual;

public class MainActivity extends AppCompatActivity implements Constans {

    private CalcLogicVisual visual;
    private AppSettings appSettings;

    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        visual = new CalcLogicVisual(findViewById(R.id.screen));
        appSettings = new AppSettings(true,false,0,0);

        Button settings = findViewById(R.id.btnSettings);
        settings.setOnClickListener(view -> {
            Intent settingsActivity = new Intent(MainActivity.this, SettingsActivity.class);
            settingsActivity.putExtra(APP_SETTINGS, appSettings);

            startActivityForResult(settingsActivity, REQUEST_CODE_SETTING_ACTIVITY);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK) {
            appSettings = data.getParcelableExtra(APP_SETTINGS);
            populateView();
        }
    }

    private void populateView() {
        setBackgroundImage(appSettings.getBackgroundID());
        isShowBackground(appSettings.isShowBackground());
        setKeyboardKeysIsCircle(appSettings.isCircleKeyboardButtons());
    }

    private void setKeyboardKeysIsCircle(boolean isCircle) {
        if (isCircle)
            setTheme(R.style.Keyboard_Circle);
        else
            setTheme(R.style.Keyboard);
    }

    private void isShowBackground(boolean isShow) {
        if (isShow)
            findViewById(R.id.backgroundImage).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.backgroundImage).setVisibility(View.INVISIBLE);
    }

    private void setBackgroundImage(int backgroundID) {
        ImageView background = findViewById(R.id.backgroundImage);
        switch (backgroundID) {
            case 0:
                background.setImageResource(R.drawable.bg);
                break;
            case 1:
                background.setImageResource(R.drawable.bg1);
                break;
            case 2:
                background.setImageResource(R.drawable.bg2);
                break;
        }
    }
}