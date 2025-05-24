package com.example.prueba

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReservasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_reservas, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvReservas)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ReservasAdapter(getDummyReservas())

        return view
    }

    private fun getDummyReservas(): List<Reserva> {
        return listOf(
            Reserva("Cancha 1", "2023-10-15 16:00", "Fútbol"),
            Reserva("Cancha 2", "2023-10-16 17:00", "Básquet")
        )
    }
}

data class Reserva(val cancha: String, val fecha: String, val deporte: String)

class ReservasAdapter(private val reservas: List<Reserva>) :
    RecyclerView.Adapter<ReservasAdapter.ViewHolder>() {

    // Clase ViewHolder (ya implementada)
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(reserva: Reserva) {
            itemView.findViewById<TextView>(R.id.tvCancha).text = reserva.cancha
            itemView.findViewById<TextView>(R.id.tvFecha).text = reserva.fecha
            itemView.findViewById<TextView>(R.id.tvDeporte).text = reserva.deporte

            itemView.findViewById<Button>(R.id.btnEditar).setOnClickListener {
                // Lógica para editar
            }

            itemView.findViewById<Button>(R.id.btnEliminar).setOnClickListener {
                // Lógica para eliminar
            }
        }
    }

    // 1. Método obligatorio: Creación del ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reserva, parent, false)
        return ViewHolder(view)
    }

    // 2. Método obligatorio: Vinculación de datos
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reservas[position])
    }

    // 3. Método obligatorio: Cantidad de elementos
    override fun getItemCount() = reservas.size
}