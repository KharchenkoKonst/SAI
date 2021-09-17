package com.example.lab1.services

import com.example.lab1.config.ALPHA
import com.example.lab1.config.FINAL_TEMPERATURE
import com.example.lab1.config.INITIAL_TEMPERATURE
import com.example.lab1.config.STEPS_PER_CHANGE
import com.example.lab1.domain.models.Solution
import com.example.lab1.domain.models.copy
import kotlin.math.exp
import kotlin.random.Random

/**
 * Симуляция восстановления
 */
class RecoverySimulation {

    /**
     * Решить задачу о размещении ферзей
     *
     * @param count число ферзей
     * @return лучшее найденное решение
     */
    fun solveQueensPuzzle(count: Int): Solution? {
        /** Оригинальное (текущее) решение */
        val currentSolution = Solution(count)

        /** Найденное (рабочее) решение */
        val workSolution = Solution(count)
        workSolution.arrangement = currentSolution.copy()

        /** Лучшее решение */
        val bestSolution = Solution(count)
        bestSolution.arrangement = currentSolution.copy()

        var temperature = INITIAL_TEMPERATURE

        var useNew = false
        while (temperature > FINAL_TEMPERATURE) {
            for (step in 0 until STEPS_PER_CHANGE) {
                workSolution.tweak()
                if (workSolution.conflictsCount <= currentSolution.conflictsCount) {
                    useNew = true
                } else {
                    val delta = workSolution.conflictsCount - currentSolution.conflictsCount
                    val calc = exp(-delta / temperature)
                    if (calc > Random.nextDouble()) {
                        useNew = true
                    }
                }
                if (useNew) {
                    useNew = false
                    currentSolution.arrangement = workSolution.copy()
                    if (currentSolution.conflictsCount < bestSolution.conflictsCount) {
                        bestSolution.arrangement = currentSolution.copy()
                    }
                } else {
                    workSolution.arrangement = currentSolution.copy()
                }
            }
            temperature *= ALPHA
        }

        if (bestSolution.conflictsCount == 0) {
            return bestSolution
        } else return null
    }
}