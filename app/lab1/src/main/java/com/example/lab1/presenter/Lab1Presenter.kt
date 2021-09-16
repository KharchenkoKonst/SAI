package com.example.lab1.presenter

import com.example.lab1.Lab1Activity
import com.example.lab1.services.RecoverySimulation

class Lab1Presenter(private val view: Lab1Activity) {

    private val recoverySimulation = RecoverySimulation()

    fun runRecoverySimulation(count: Int) {
        recoverySimulation.solveQueensPuzzle(count)
    }
}