package com.example.lab1.view.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.lab1.R
import com.example.lab1.R.id
import com.example.lab1.R.layout
import com.example.lab1.domain.models.Solution
import com.example.lab1.view.fragments.GraphFragment
import com.example.lab1.view.fragments.FieldFragment
import com.example.lab1.presenter.Lab1Presenter
import com.jjoe64.graphview.series.DataPoint

class Lab1ActivityImpl : AppCompatActivity(), Lab1Activity {

    private lateinit var presenter: Lab1Presenter
    private lateinit var fieldFragment: FieldFragment
    private lateinit var graphFragment: GraphFragment

    private lateinit var prefs: SharedPreferences
    private var count = 0
    private var stepsPerChange = 0
    private var initialTemperature = 0.0
    private var finalTemperature = 0.0
    private var alpha = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_lab1)

        val imageFragment = FieldFragment()
        val graphFragment = GraphFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(id.graph_fragment, graphFragment)
        transaction.add(id.field_fragment, imageFragment)
        transaction.commit()

        setSupportActionBar(findViewById(id.toolbar))
        prefs = getDefaultSharedPreferences(this)


        presenterInit()
    }

    override fun onStart() {
        super.onStart()
        fieldFragment = supportFragmentManager.findFragmentById(id.field_fragment) as FieldFragment
        graphFragment = supportFragmentManager.findFragmentById(id.graph_fragment) as GraphFragment
    }

    private fun presenterInit() {
        presenter = Lab1Presenter(this)
    }

    fun onRefreshClick(view: View) {
        count = prefs.getString("queens_count", "8")?.toInt()!!
        stepsPerChange = prefs.getString("steps_per_change", "100")?.toInt()!!
        initialTemperature = prefs.getString("initial_temperature", "30.0")?.toDouble()!!
        finalTemperature = prefs.getString("final_temperature", "0.5")?.toDouble()!!
        alpha = prefs.getString("alpha", "0.98")?.toDouble()!!

//        val count = findViewById<EditText>(id.count).text.toString().toInt()
        if (count >= 4) {
            presenter.runRecoverySimulation(count, stepsPerChange, initialTemperature, finalTemperature, alpha)
        }
    }

    private fun scaleField(count: Int) {
        fieldFragment.changeField(count)
    }

    override fun showSolutionOnField(count: Int, solution: Solution) {
        scaleField(count)
        fieldFragment.showSolution(solution)
    }

    override fun showGraph(logs: List<Array<DataPoint>>) {
        graphFragment.setData(logs)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            id.settings -> {
                item.setIntent(Intent(this, PreferencesActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}