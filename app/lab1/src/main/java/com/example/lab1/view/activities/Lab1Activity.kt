package com.example.lab1.view.activities

import com.example.lab1.domain.models.Solution
import com.jjoe64.graphview.series.DataPoint

interface Lab1Activity {

    fun showSolutionOnField(count: Int, solution: Solution)
    fun showGraph(logs: List<Array<DataPoint>>)
}