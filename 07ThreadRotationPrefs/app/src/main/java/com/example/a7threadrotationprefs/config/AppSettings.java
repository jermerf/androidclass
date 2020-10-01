package com.example.a7threadrotationprefs.config;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

public class AppSettings {
    public static final String PREFERENCES_NAME = "fluffer";
    public static final String PREF_FLUFFINESS = "fluffiness";
    public static final String PREF_ROLE = "role";
    public static final String PREF_STATUS = "status";

    public enum Status { ACTIVE, DISABLED, DISALLOWED;
        public static Status fromString (String statusString) {
            try {
                return valueOf(statusString);
            } catch (Exception ex) {
                // For error cases
                return DISABLED;
            }
        }
    }

    private ContextWrapper ctx;

    public AppSettings(ContextWrapper ctx) {
        this.ctx = ctx;
    }

    private SharedPreferences getPrefs() {
        return ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void saveSettings(int fluffiness, String role, Status status){
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putInt(PREF_FLUFFINESS, fluffiness);
        editor.putString(PREF_ROLE, role);
        editor.putString(PREF_STATUS, status.toString());
        editor.commit();
    }

    public int getFluffiness(){
        return getPrefs().getInt(PREF_FLUFFINESS, 0);
    }
    public String getRole(){
        return getPrefs().getString(PREF_ROLE, "Catbassador");
    }
    public Status getStatus(){
        return Status.fromString(getPrefs().getString(PREF_STATUS, Status.DISABLED.toString()));
    }

}
