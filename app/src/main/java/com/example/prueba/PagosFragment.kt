package com.example.prueba

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_pagos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvPagos)
        recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.adapter = PagosAdapter(getDummyPagos())

        view.findViewById<Button>(R.id.btnExportar).setOnClickListener {
            exportarExcel()
        }

        return view
    }

    private fun exportarExcel() {
        // Lógica para exportar a Excel
    }
}