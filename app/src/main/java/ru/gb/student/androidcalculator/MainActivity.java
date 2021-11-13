package ru.gb.student.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.gb.student.androidcalculator.logic.CalcLogicVisual;

public class MainActivity extends AppCompatActivity {

    private CalcLogicVisual visual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visual = new CalcLogicVisual(findViewById(R.id.screen));
    }
}