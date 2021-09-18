package com.example.lab1.view.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab1.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer.LegendAlign.TOP
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class GraphFragment : Fragment() {

    private lateinit var graph: GraphView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        graph = view.findViewById(R.id.grapg)
        graph.viewport.isScalable = true
        graph.legendRenderer.backgroundColor = Color.argb(0.1f, 0f, 0f, 0f)
        graph.legendRenderer.align = TOP
    }

    fun setData(logs: List<Array<DataPoint>>) {
        graph.removeAllSeries()
        graph.legendRenderer.isVisible = true
        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinX(0.0)
        graph.viewport.setMaxX(logs[0].size.toDouble())

        val bestSolution = LineGraphSeries(logs[0])
        bestSolution.title = "Лучшее решение"
        bestSolution.color = Color.RED

        val wrongDecisions = LineGraphSeries(logs[1])
        wrongDecisions.title = "Неверные решения"
        wrongDecisions.color = Color.GREEN

        val temperature = LineGraphSeries(logs[2])
        temperature.title = "Температура"

        graph.addSeries(temperature)
        graph.addSeries(wrongDecisions)
        graph.addSeries(bestSolution)
    }
}