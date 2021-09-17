package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.lab1.domain.models.Solution
import com.example.lab1.fragments.GraphFragment
import com.example.lab1.fragments.FieldFragment
import com.example.lab1.presenter.Lab1Presenter

class Lab1ActivityImpl : AppCompatActivity(), Lab1Activity {

    private lateinit var presenter: Lab1Presenter
    private lateinit var fieldFragment: FieldFragment

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

    override fun onStart() {
        super.onStart()
        fieldFragment = supportFragmentManager.findFragmentById(R.id.field_fragment) as FieldFragment
    }

    private fun presenterInit() {
        presenter = Lab1Presenter(this)
    }

    fun onRefreshClick(view: View) {
        val count = findViewById<EditText>(R.id.count).text.toString().toInt()
        if (count >= 4) {
            presenter.runRecoverySimulation(count)
        }
    }

    private fun scaleField(count: Int) {
        fieldFragment.changeField(count)
    }

    override fun showSolutionOnField(count: Int, solution: Solution) {
        scaleField(count)
        fieldFragment.showSolution(solution)
    }
}