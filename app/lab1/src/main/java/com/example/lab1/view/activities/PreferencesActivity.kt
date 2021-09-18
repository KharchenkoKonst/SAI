package com.example.lab1.view.activities

import android.os.Bundle
import android.preference.PreferenceActivity
import com.example.lab1.R

class PreferencesActivity : PreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
    }
}