package com.example.lab1.presenter

import com.example.lab1.view.activities.Lab1Activity
import com.example.lab1.domain.models.Solution
import com.example.lab1.domain.services.RecoverySimulation
import com.jjoe64.graphview.series.DataPoint

class Lab1Presenter(private val view: Lab1Activity) {

    private val recoverySimulation = RecoverySimulation()

    fun runRecoverySimulation(
        count: Int,
        stepsPerChange: Int,
        initialTemperature: Double,
        finalTemperature: Double,
        alpha: Double
    ) {
        val res = recoverySimulation.solveQueensPuzzle(
            count,
            stepsPerChange,
            initialTemperature,
            finalTemperature,
            alpha
        )
        res?.let {

            val solution = res[0] as Solution
            val logs = listOf(
                res[1] as Array<DataPoint>,
                res[2] as Array<DataPoint>,
                res[3] as Array<DataPoint>
            )
            view.showSolutionOnField(count, solution)
            view.showGraph(logs)
        }
    }
}