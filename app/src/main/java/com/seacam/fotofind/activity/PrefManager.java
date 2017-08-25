package com.seacam.fotofind.activity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jensensc on 8/25/17.
 */

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTImeLaunch";

    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
