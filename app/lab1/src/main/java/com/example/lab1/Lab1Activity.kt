package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.lab1.fragments.GraphFragment
import com.example.lab1.fragments.FieldFragment

class Lab1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1)

        val imageFragment = FieldFragment()
        val graphFragment = GraphFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.graph_fragment, graphFragment)
        transaction.add(R.id.field_fragment, imageFragment)
        transaction.commit()
    }

    fun onRefreshClick(view: android.view.View) {
        val n = findViewById<EditText>(R.id.count).text.toString().toInt()
        val fieldFragment = supportFragmentManager.findFragmentById(R.id.field_fragment) as FieldFragment
        Log.d("testFrag", fieldFragment.toString())
        fieldFragment.changeField(n)
    }
}