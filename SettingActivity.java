package com.example.broadcastreceiverapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingActivity extends AppCompatActivity {

    private SwitchMaterial switchDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load saved preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);

        // Apply the theme before setting the content view
        setTheme(isDarkMode);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize UI components
        initializeUI(sharedPreferences, isDarkMode);
    }

    private void initializeUI(SharedPreferences sharedPreferences, boolean isDarkMode) {
        switchDarkMode = findViewById(R.id.switch_dark_mode);
        switchDarkMode.setChecked(isDarkMode);

        // Listener for switch change
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save the preference
            saveThemePreference(sharedPreferences, isChecked);

            // Apply the theme
            setTheme(isChecked);

            // Recreate activity to apply the new theme
            recreate();
        });
    }

    private void saveThemePreference(SharedPreferences sharedPreferences, boolean isDarkMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("dark_mode", isDarkMode);
        editor.apply();
    }

    private void setTheme(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
