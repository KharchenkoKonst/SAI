package com.example.lab1.domain.models

import kotlin.random.Random

/**
 * Оодно из решений задачи о ферзях
 *
 * @param count число ферзей
 */
class Solution(private val count: Int) {

    /** Расположение ферзей */
    var arrangement = mutableListOf<Int>()
        set(value) {
            field = value
            calculateConflicts()
        }

    /** Число конфликтов */
    var conflictsCount: Int = 100
        private set

    init {
        for (position in 0 until count) {
            arrangement.add(position)
        }
        calculateConflicts()
    }

    fun tweak() {
        val index1 = rand()
        var index2: Int
        do {
            index2 = rand()
        } while (index1 == index2)

        arrangement[index1] = arrangement[index2].also {
            arrangement[index2] = arrangement[index1]
        }

        calculateConflicts()
    }

    private fun calculateConflicts() {
        val dx = listOf(-1, 1, -1, 1)
        val dy = listOf(-1, 1, 1, -1)

//        val field = mutableListOf(mutableListOf<Char>())
        val field = Array(count) { CharArray(count) }
        var conflicts = 0

        //распологаем фигуры на 2м массиве
        for (i in 0 until count) {
            field[i][arrangement[i]] = 'Q'
        }

        for (i in 1 until count) {
            val x = i
            val y = arrangement[i]
            for (j in 0..3) {
                var tempX = x
                var tempY = y
                while (true) {
                    tempX += dx[j]
                    tempY += dy[j]
                    if (tempX < 0 || tempX >= count || tempY < 0 || tempY >= count)
                        break
                    if (field[tempX][tempY] == 'Q')
                        conflicts++
                }
            }
        }
        conflictsCount = conflicts
    }

    private fun rand() = Random.nextInt(until = count)
}

fun Solution.copy() = arrangement.toMutableList()
