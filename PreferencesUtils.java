package com.example.broadcastreceiverapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {
    private static final String PREFERENCES_FILE = "app_preferences";
    private static final String THEME_KEY = "app_theme";

    public static void saveThemePreference(Context context, boolean isDarkMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(THEME_KEY, isDarkMode);
        editor.apply();
    }

    public static boolean isDarkMode(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(THEME_KEY, false); // Default to light mode
    }
}
