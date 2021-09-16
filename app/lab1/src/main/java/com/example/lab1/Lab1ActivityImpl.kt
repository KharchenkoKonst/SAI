package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.lab1.fragments.GraphFragment
import com.example.lab1.fragments.FieldFragment
import com.example.lab1.presenter.Lab1Presenter

class Lab1ActivityImpl : AppCompatActivity(), Lab1Activity {

    private lateinit var presenter: Lab1Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1)

        val imageFragment = FieldFragment()
        val graphFragment = GraphFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.graph_fragment, graphFragment)
        transaction.add(R.id.field_fragment, imageFragment)
        transaction.commit()

        presenterInit()
    }

    private fun presenterInit() {
        presenter = Lab1Presenter(this)
    }

    fun onRefreshClick(view: View) {
        val count = findViewById<EditText>(R.id.count).text.toString().toInt()
        if (count >= 4) {
            scaleField(count)
            presenter.runRecoverySimulation(count)
        }
    }

    private fun scaleField(count: Int) {
        val fieldFragment = supportFragmentManager.findFragmentById(R.id.field_fragment) as FieldFragment
        fieldFragment.changeField(count)
    }
}