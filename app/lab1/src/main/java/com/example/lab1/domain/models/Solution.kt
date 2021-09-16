package com.example.lab1.domain.models

import android.util.Log
import kotlin.random.Random

/**
 * Оодно из решений задачи о ферзях
 *
 * @param count число ферзей
 */
class Solution(private val count: Int) {

    /** Расположение ферзей */
    val arrangement = mutableListOf<Int>()

    /** Число конфликтов */
    var conflictsCount: Int? = null
        private set

    init {
        for (position in 0 until count) {
            arrangement.add(position)
        }
        Log.d("arrangement", "$arrangement")
        tweak()
    }

    private fun calculateConflicts(): Int {
        TODO()
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
    }

    private fun rand() = Random.nextInt(until = count)
}
