package com.anggi.belajaranggi.Storage;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareprefManager_Coffeeshop {
    public static final String SP_LOGIN_APP = "sp_Coffeeshop";

    public static final String SP_ID = "spId_Coffeeshop";
    public static final String SP_NAMA = "spNama";
    public static final String SP_ALAMAT = "spAlamat";
    public static final String SP_NO_TELPON= "spNo_Telpon";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_JAMBUKA = "spJambuka";
    public static final String SP_JAMTUTUP = "spJamtutup";
    public static final String SP_STATUS = "spStatus";
    public static final String SP_GAMBAR = "spGambar";
    public static final String SP_LONGITUDE = "spLongitude";
    public static final String SP_LATITUDE = "spLatitude";
    public static final String SP_RATING = "spERating";

    public static final String SP_SUDAH_LOGIN = "SudahLogin";
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public ShareprefManager_Coffeeshop(Context context){
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

    public String getSpId(){
        return sp.getString(SP_ID, "");
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSPAlamat(){
        return sp.getString(SP_ALAMAT, "");
    }

    public String getSPTelpon(){ return sp.getString(SP_NO_TELPON, ""); }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSpJambuka(){
        return sp.getString(SP_JAMBUKA, "");
    }

    public String getSpJamtutup(){
        return sp.getString(SP_JAMTUTUP, "");
    }

    public String getSpStatus(){
        return sp.getString(SP_STATUS, "");
    }

    public String getSpGambar(){
        return sp.getString(SP_GAMBAR, "");
    }

    public String getSpLongitude(){
        return sp.getString(SP_LONGITUDE, "");
    }

    public String getSpLatitude(){
        return sp.getString(SP_LATITUDE, "");
    }

    public String getSpRating(){
        return sp.getString(SP_RATING, "");
    }

    public Boolean getSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
