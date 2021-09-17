package com.example.lab1.presenter

import android.util.Log
import com.example.lab1.Lab1Activity
import com.example.lab1.services.RecoverySimulation

class Lab1Presenter(private val view: Lab1Activity) {

    private val recoverySimulation = RecoverySimulation()

    fun runRecoverySimulation(count: Int) {
        Log.d("bestSolution", "${recoverySimulation.solveQueensPuzzle(count).arrangement}")
    }
}