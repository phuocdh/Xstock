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

    private static final String KEY_PREF_X24BASIC_LICENSE = "x24basic_license";
    private static final String KEY_PREF_X24TRADERS_LICENSE = "X24TRADERS_LICENSE";
    private static final String KEY_PREF_X24PLUS_LICENSE = "x24plus_license";
    private static final String KEY_PREF_NEWS_LICENSE = "news_license";
    private static final String KEY_PREF_INDEXINFO_LICENSE = "indexinfo_license";
    private static final String KEY_PREF_5TRADE_LICENSE = "5trade_license";
    private static final String KEY_PREF_10TRADE_LICENSE = "10trade_license";
    private static final String KEY_PREF_15TRADE_LICENSE = "15trade_license";
    private static final String KEY_PREF_GROUP_ID = "group_id";

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

    public void SetPrefOnOff(Boolean isOnOff) {
        editor.putBoolean(KEY_PREF_ON_OFF, isOnOff);
        editor.commit();
    }

    public Boolean GetPrefOnOff() {
        return pref.getBoolean(KEY_PREF_ON_OFF, false);
    }

    public void SetPrefDeviceToken(String token) {
        editor.putString(KEY_PREF_DEVICE_TOKEN, token);
        editor.commit();
    }

    public String GetPrefDeviceToken() {
        return pref.getString(KEY_PREF_DEVICE_TOKEN, "");
    }

    public void SetPrefX24BasicLicense(Boolean isX24BasicLicense) {
        editor.putBoolean(KEY_PREF_X24BASIC_LICENSE, isX24BasicLicense);
        editor.commit();
    }

    public Boolean GetPrefX24BasicLicense() {
        return pref.getBoolean(KEY_PREF_X24BASIC_LICENSE, false);
    }

    public void SetPrefX24TradersLicense(Boolean is24TradersLicense) {
        editor.putBoolean(KEY_PREF_X24TRADERS_LICENSE, is24TradersLicense);
        editor.commit();
    }

    public Boolean GetPrefX24TradersLicense() {
        return pref.getBoolean(KEY_PREF_X24TRADERS_LICENSE, false);
    }

    public void SetPrefX24PlusLicense(Boolean isX24PlusLicense) {
        editor.putBoolean(KEY_PREF_X24PLUS_LICENSE, isX24PlusLicense);
        editor.commit();
    }

    public Boolean GetPrefX24PlusLicense() {
        return pref.getBoolean(KEY_PREF_X24PLUS_LICENSE, false);
    }

    public void SetPrefNewsLicense(Boolean isNewsLicense) {
        editor.putBoolean(KEY_PREF_NEWS_LICENSE, isNewsLicense);
        editor.commit();
    }

    public Boolean GetPrefNewsLicense() {
        return pref.getBoolean(KEY_PREF_NEWS_LICENSE, false);
    }

    public void SetPrefIndexInfoLicense(Boolean isIndexInfoLicense) {
        editor.putBoolean(KEY_PREF_INDEXINFO_LICENSE, isIndexInfoLicense);
        editor.commit();
    }

    public Boolean GetPrefIndexInfoLicense() {
        return pref.getBoolean(KEY_PREF_INDEXINFO_LICENSE, false);
    }

    public void SetPref5TradeLicense(Boolean is5TradeLicense) {
        editor.putBoolean(KEY_PREF_5TRADE_LICENSE, is5TradeLicense);
        editor.commit();
    }

    public Boolean getPref5TradeLicense() {
        return pref.getBoolean(KEY_PREF_5TRADE_LICENSE, false);
    }

    public void SetPref10TradeLicense(Boolean is10TradeLicense) {
        editor.putBoolean(KEY_PREF_10TRADE_LICENSE, is10TradeLicense);
        editor.commit();
    }

    public Boolean GetPref10TradeLicense() {
        return pref.getBoolean(KEY_PREF_10TRADE_LICENSE, false);
    }

    public void SetPref15TradeLicense(Boolean is15TradeLicense) {
        editor.putBoolean(KEY_PREF_15TRADE_LICENSE, is15TradeLicense);
        editor.commit();
    }

    public Boolean GetPref15TradeLicense() {
        return pref.getBoolean(KEY_PREF_15TRADE_LICENSE, false);
    }

    public void SetPrefGroupID(int groupID) {
        editor.putInt(KEY_PREF_GROUP_ID, groupID);
        editor.commit();
    }

    public int GetPrefGroupID() {
        return pref.getInt(KEY_PREF_GROUP_ID, 0);
    }
}
