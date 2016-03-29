package com.cabe.lib.ui.rowview.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * RowView Demo Activity
 * Created by cabe on 16/3/29.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void onClick(View view) {
        Log.w("MainActivity", view.getTag() + "");
    }
}
