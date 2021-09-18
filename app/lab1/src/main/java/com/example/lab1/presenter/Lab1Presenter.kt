package com.example.lab1.presenter

import android.util.Log
import com.example.lab1.Lab1Activity
import com.example.lab1.domain.models.Solution
import com.example.lab1.services.RecoverySimulation
import com.jjoe64.graphview.series.DataPoint

class Lab1Presenter(private val view: Lab1Activity) {

    private val recoverySimulation = RecoverySimulation()

    fun runRecoverySimulation(count: Int) {
        val res = recoverySimulation.solveQueensPuzzle(count)
        res?.let {
            val solution = res[0] as Solution
            val logBestSolution = res[1] as Array<DataPoint>
            val logWrongDecisions = res[2] as Array<DataPoint>
            view.showSolutionOnField(count, solution)
            view.showBestSolutionLog(logBestSolution)
        }
    }
}