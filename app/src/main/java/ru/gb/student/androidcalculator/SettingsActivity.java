package ru.gb.student.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import com.google.android.material.chip.Chip;

public class SettingsActivity extends AppCompatActivity implements Constans {

    int[] themeRadiobutonnsIds;
    int[] backgroundRadiobutonnsIds;

    public SettingsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();

        AppSettings settings = getIntent().getExtras().getParcelable(APP_SETTINGS);
        populateView(settings);
    }

    private void initView() {
        findViewById(R.id.btnBack).setOnClickListener(view -> {
            Intent intentResult = new Intent();
            intentResult.putExtra(APP_SETTINGS, createSettings());
            setResult(RESULT_OK, intentResult);
            finish();
        });

        themeRadiobutonnsIds = new int[]{R.id.rbDefaultTheme, R.id.rbColoredTheme};
        backgroundRadiobutonnsIds = new int[]{R.id.rbBackgrondImage1, R.id.rbBackgrondImage2, R.id.rbBackgrondImage3};
    }

    private AppSettings createSettings() {
        Chip chipBG = findViewById(R.id.chipBackground);
        Chip chipKB = findViewById(R.id.chipCicleButtons);

        AppSettings settings = new AppSettings(
                chipBG.isChecked(), chipKB.isChecked(), whoisBackgroundIsChecked(), whoisThemeIsChecked()
        );
        return settings;
    }

    private void populateView(AppSettings settings) {
        Chip chipBG = findViewById(R.id.chipBackground);
        chipBG.setChecked(settings.isShowBackground());

        Chip chipKB = findViewById(R.id.chipCicleButtons);
        chipKB.setChecked(settings.isCircleKeyboardButtons());


        for (int i = 0; i < themeRadiobutonnsIds.length; i++) {
            RadioButton rb = findViewById(themeRadiobutonnsIds[i]);
            if (settings.getKeyboardThemeID() == i)
                rb.setChecked(true);
        }

        for (int i = 0; i < backgroundRadiobutonnsIds.length; i++) {
            RadioButton rb = findViewById(backgroundRadiobutonnsIds[i]);
            if (settings.getBackgroundID() == i)
                rb.setChecked(true);
        }
    }

    private int whoisBackgroundIsChecked() {
        for (int i = 0; i < backgroundRadiobutonnsIds.length; i++) {
            RadioButton rb = findViewById(backgroundRadiobutonnsIds[i]);
            if (rb.isChecked())
                return i;
        }
        return 0;
    }

    private int whoisThemeIsChecked() {
        for (int i = 0; i < themeRadiobutonnsIds.length; i++) {
            RadioButton rb = findViewById(themeRadiobutonnsIds[i]);
            if (rb.isChecked())
                return i;
        }
        return 0;
    }
}