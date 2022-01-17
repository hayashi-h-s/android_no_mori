package com.haya.android_no_mori.ui.sample.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haya.android_no_mori.R

class SampleDialogsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_dialogs)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SampleDialogsFragment.newInstance())
                .commitNow()
        }
    }
}