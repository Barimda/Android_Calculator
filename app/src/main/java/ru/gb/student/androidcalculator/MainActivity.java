package ru.gb.student.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ToggleButton;

import ru.gb.student.androidcalculator.logic.CalcLogicVisual;

public class MainActivity extends AppCompatActivity {

    private CalcLogicVisual visual;

    // Имя настроек
    private static final String NameSharedPreference = "CalculatorAppKeyboardTheme";

    // Имя параметра в настройках


    private static final String appTheme = "APP_THEME";
    private static final int DefaultTheme = 0;
    private static final int CircleKeyboardTheme = 1;

    private ToggleButton changeKeyboardTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visual = new CalcLogicVisual(findViewById(R.id.screen));

    }

}