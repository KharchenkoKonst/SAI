package com.example.lab1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab1.R
import com.example.lab1.domain.models.Solution
import com.example.lab1.view.custom_views.FieldView

class FieldFragment : Fragment() {

    private lateinit var field: FieldView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_field, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        field = view.findViewById(R.id.field_view)
    }

    fun changeField(n: Int) {
        field.n = n
        field.invalidate()
    }

    fun showSolution(solution: Solution) {
        field.solution = solution
        field.invalidate()
    }
}