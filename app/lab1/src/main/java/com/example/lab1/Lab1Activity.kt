package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab1.fragments.GraphFragment
import com.example.lab1.fragments.ImageFragment

class Lab1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1)

        val imageFragment = ImageFragment()
        val graphFragment = GraphFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.graph_view, graphFragment)
        transaction.add(R.id.image_view, imageFragment)
        transaction.commit()
    }
}