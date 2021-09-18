package com.example.lab1.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab1.R
import com.jjoe64.graphview.CursorMode
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.DataPointInterface
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.Series
import kotlin.Int.Companion

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
        graph = view.findViewById<GraphView>(R.id.grapg)
/*
        graph.title = "123123"

        val data = LineGraphSeries(
            arrayOf(
                DataPoint(1.0, 2.0),
                DataPoint(2.0, 6.0),
                DataPoint(3.0, 10.0),
            )
        )
        data.color = Color.GREEN
//        data.isDrawBackground = true
        graph.addSeries(data)

        val data2 = LineGraphSeries(
            arrayOf(
                DataPoint(1.0, 12.0),
                DataPoint(4.0, 16.0),
                DataPoint(6.0, 20.0),
            )
        )
        graph.addSeries(data2)
*/
    }

    fun setData(log: Array<DataPoint>) {
        graph.viewport.isScalable = true
        graph.viewport.scrollToEnd()
//        graph.viewport.isScrollable = true
        graph.removeAllSeries()
        val series = LineGraphSeries(log)
        graph.addSeries(series)
    }
}