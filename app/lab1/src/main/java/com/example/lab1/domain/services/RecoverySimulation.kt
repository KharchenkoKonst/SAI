package com.example.lab1.domain.services

import com.example.lab1.domain.models.Solution
import com.example.lab1.domain.models.copy
import com.jjoe64.graphview.series.DataPoint
import kotlin.math.exp
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * Симуляция восстановления
 */
class RecoverySimulation {

    //лог решений для вывода графика
    private val logBestSolution = mutableListOf<DataPoint>()
    private val logWrongDecisions = mutableListOf<DataPoint>()
    private val logTemperature = mutableListOf<DataPoint>()

    /**
     * Решить задачу о размещении ферзей
     *
     * @param COUNT число ферзей
     * @param STEPS_PER_CHANGE число итераций на единицу температуры
     * @param INITIAL_TEMPERATURE начальная температура
     * @param FINAL_TEMPERATURE конечная температура
     * @param ALPHA альфа
     *
     * @return лучшее найденное решение
     *
     */
    fun solveQueensPuzzle(
        COUNT: Int,
        STEPS_PER_CHANGE: Int,
        INITIAL_TEMPERATURE: Double,
        FINAL_TEMPERATURE: Double,
        ALPHA: Double
    ): List<Any>? {
        logBestSolution.clear()
        logWrongDecisions.clear()
        logTemperature.clear()
        var wrongDecisions = 0
        var iteration = 0

        /** Оригинальное (текущее) решение */
        val currentSolution = Solution(COUNT)

        /** Найденное (рабочее) решение */
        val workSolution = Solution(COUNT)
        workSolution.arrangement = currentSolution.copy()

        /** Лучшее решение */
        val bestSolution = Solution(COUNT)
        bestSolution.arrangement = currentSolution.copy()

        var temperature = INITIAL_TEMPERATURE

        var useNew = false
        while (temperature > FINAL_TEMPERATURE) {
            wrongDecisions = 0
            for (step in 0 until STEPS_PER_CHANGE) {
                workSolution.tweak()
                if (workSolution.conflictsCount <= currentSolution.conflictsCount) {
                    useNew = true
                } else {
                    val delta = workSolution.conflictsCount - currentSolution.conflictsCount
                    val calc = exp(-delta / temperature)
                    if (calc > Random.nextDouble()) {
                        wrongDecisions++
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

            //График температуры
            logTemperature.add(
                DataPoint(iteration.toDouble(), temperature)
            )

            //График принятых неверных решений
            logWrongDecisions.add(
                DataPoint(iteration.toDouble(), wrongDecisions.toDouble())
            )

            //График лучших решений
            logBestSolution.add(
                DataPoint(
                    iteration.toDouble(), bestSolution.conflictsCount.toDouble()
                )
            )

            iteration++
            temperature *= ALPHA
        }

        return if (bestSolution.conflictsCount == 0) {
            listOf(
                bestSolution, logBestSolution.toTypedArray(), logWrongDecisions.toTypedArray(),
                logTemperature.toTypedArray()
            )
        } else null
    }
}