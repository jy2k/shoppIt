package com.jonmal.shoppit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;

import com.jonmal.shoppit.MainApplication;
import com.jonmal.shoppit.R;
import com.jonmal.shoppit.views.AddItemFragment;

public class AddItemActivity extends Activity {

    //==========================================================
    //                  Constants
    //==========================================================

    public static final int ACTIVITY_ADD_ITEM = 1;

    //==========================================================
    //                  Activity Lifecycle
    //==========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, AddItemFragment.newInstance())
                    .commit();
        }
    }

    public void onItemSubmitted(Intent data) {

        setResult(Activity.RESULT_OK, data);

        Vibrator vibrator = (Vibrator) MainApplication.getAppContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);

        finish();
    }
}