package com.anggi.belajaranggi.Storage;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareprefManager {
    public static final String SP_LOGIN_APP = "sp_Coffeeshop";

    public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_TELPON= "spTelpon";
    public static final String SP_ALAMAT = "spAlamat";
    public static final String SP_SUDAH_LOGIN = "SudahLogin";
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public ShareprefManager(Context context){
        sp = context.getSharedPreferences(SP_LOGIN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSPAlamat(){
        return sp.getString(SP_ALAMAT, "");
    }

    public String getSPTelpon(){
        return sp.getString(SP_TELPON, "");
    }

    public Boolean getSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

}
