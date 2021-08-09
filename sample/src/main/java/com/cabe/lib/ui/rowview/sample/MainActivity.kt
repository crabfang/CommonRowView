package com.cabe.lib.ui.rowview.sample

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View

/**
 * RowView Demo Activity
 * Created by cabe on 16/3/29.
 */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    fun onClick(view: View) {
        Log.w("MainActivity", view.tag.toString() + "")
    }
}