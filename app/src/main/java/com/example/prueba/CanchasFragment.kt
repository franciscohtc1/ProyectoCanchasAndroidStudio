package com.example.prueba

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CanchasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_canchas, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCanchas)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = CanchasAdapter(getDummyData())

        return view
    }

    private fun getDummyData(): List<Cancha> {
        return listOf(
            Cancha("Cancha 1", "Av. Principal 123", R.drawable.ic_logo),
            Cancha("Cancha 2", "Av. Principal 123", R.drawable.ic_logo),
            Cancha("Cancha 3", "Av. Principal 123", R.drawable.ic_logo)
        )
    }
}

data class Cancha(val nombre: String, val direccion: String, val imagen: Int)

class CanchasAdapter(private val canchas: List<Cancha>) :
    RecyclerView.Adapter<CanchasAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cancha: Cancha) {
            itemView.findViewById<TextView>(R.id.tvNombre).text = cancha.nombre
            itemView.findViewById<TextView>(R.id.tvDireccion).text = cancha.direccion
            itemView.findViewById<ImageView>(R.id.ivImagen).setImageResource(cancha.imagen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cancha, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(canchas[position])
    }

    override fun getItemCount() = canchas.size
}