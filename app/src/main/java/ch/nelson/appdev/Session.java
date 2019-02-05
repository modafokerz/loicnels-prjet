package ch.nelson.appdev;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setEmail(String email) {
        prefs.edit().putString("email", email).commit();
    }

    public String getEmail() {
        String email = prefs.getString("email","");
        return email;
    }

    public void setPassword(String passwd) {
        prefs.edit().putString("password", passwd).commit();
    }

    public String getPassword() {
        String passwd = prefs.getString("password","");
        return passwd;
    }

    public String getLangPref() {
        String langPref = prefs.getString("langPref", "");
        return langPref;
    }

    public void setIsConnected(Integer isConnected) {
        prefs.edit().putInt("isConnected", isConnected).commit();
    }

    public Integer getIsConnected() {
        Integer isConnected = prefs.getInt("isConnected",0);
        return isConnected;
    }
}
