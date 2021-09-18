package com.example.sai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lab1.view.activities.Lab1ActivityImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_lab1 -> {
                Intent(this, Lab1ActivityImpl::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

}