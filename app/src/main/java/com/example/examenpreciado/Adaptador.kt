package com.example.examenpreciado

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenpreciado.databinding.ItemBinding
import com.squareup.picasso.Picasso

class Adaptador(val lista: List<Matricula>): RecyclerView.Adapter<Adaptador.ViewHolderMatricula>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMatricula {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)

        return ViewHolderMatricula(view)
    }

    override fun onBindViewHolder(holder: ViewHolderMatricula, position: Int) {
        val item = lista[position]
        holder.bin(item)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class ViewHolderMatricula(ItemView: View):RecyclerView.ViewHolder(ItemView){
        var binding:ItemBinding = ItemBinding.bind(ItemView)

        fun bin(item: Matricula){
            binding.lblEstudiante.text = item.estudiante
            binding.lblMateria.text = item.materia
            binding.lblCupos.text = item.cupos.toString()
            binding.lblFecha.text = item.fecha
            Picasso.get().load(item.urlImagen).into(binding.imagen)
        }
    }
}