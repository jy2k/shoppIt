package com.jonmal.shoppit.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.jonmal.shoppit.MainApplication;
import com.jonmal.shoppit.R;

public class SettingsActivity extends Activity {

    //==========================================================
    //                  Constants
    //==========================================================

    public static final String PREF_LOCK_SHOPPING_LIST = "com.jonmal.PREF_LOCK_SHOPPING_LIST";

    //==========================================================
    //                  Fields
    //==========================================================

    //ui references
    private CheckBox mLockShoppingListCB;

    //==========================================================
    //                  Activity Lifecycle
    //==========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mLockShoppingListCB = (CheckBox) findViewById(R.id.activitySettings_lockListCB);

        final SharedPreferences sharedPreferences = MainApplication.getSharedPreferences();
        boolean shouldLockCheckList = sharedPreferences.getBoolean(PREF_LOCK_SHOPPING_LIST, false);

        mLockShoppingListCB.setChecked(shouldLockCheckList);
        mLockShoppingListCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mLockShoppingListCB.setChecked(isChecked);
                sharedPreferences.edit().putBoolean(PREF_LOCK_SHOPPING_LIST, isChecked).apply();
            }
        });

    }
}
