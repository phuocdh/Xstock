package com.xstock.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_XSTOCK = "xstock.vn";

    private static final String KEY_PREF_TOKEN = "token";
    private static final String KEY_PREF_SAVE_PASS = "save_pass";
    private static final String KEY_PREF_ON_OFF = "on_off";
    private static final String KEY_PREF_DEVICE_TOKEN = "devicetoken";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_XSTOCK, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void SetPrefToken(String token) {
        editor.putString(KEY_PREF_TOKEN, token);
        editor.commit();
    }

    public String GetPrefToken() {
        return pref.getString(KEY_PREF_TOKEN, "");
    }

    public void SetPrefSavePass(Boolean isSavePass) {
        editor.putBoolean(KEY_PREF_SAVE_PASS, isSavePass);
        editor.commit();
    }

    public Boolean GetPrefSavePass() {
        return pref.getBoolean(KEY_PREF_SAVE_PASS, false);
    }

    public void SetPrefOnOff(Boolean token) {
        editor.putBoolean(KEY_PREF_ON_OFF, token);
        editor.commit();
    }

    public Boolean GetPrefOnOff() {
        return pref.getBoolean(KEY_PREF_ON_OFF, false);
    }

    public void SetPrefDeviceToken(String isOnOff) {
        editor.putString(KEY_PREF_DEVICE_TOKEN, isOnOff);
        editor.commit();
    }

    public String GetPrefDeviceToken() {
        return pref.getString(KEY_PREF_DEVICE_TOKEN, "");
    }
}
