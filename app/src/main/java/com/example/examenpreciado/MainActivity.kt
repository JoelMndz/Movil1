package com.example.examenpreciado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenpreciado.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var matriculas = mutableListOf<Matricula>()
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                filtrarPorMovil1()
            }
        })
        //Agregamos los datos a firebase
        //crearDatos()
        //Cargar datos de firebase
        cargarDatos()
    }

    fun cargarDatos(){
        db.collection("matriculas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("DATA", "${document.id} => ${document.data}")
                    matriculas.add(Matricula(
                        document.data.get("estudiante").toString(),
                        document.data.get("fecha").toString(),
                        document.data.get("materia").toString(),
                        Integer.parseInt(document.data.get("cupos").toString()),
                        document.data.get("urlImagen").toString(),
                    ));
                }
                binding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = Adaptador(matriculas)
                }
            }
    }

    fun crearDatos(){
        db.collection("matriculas")
            .add(Matricula("Preciado Valverde","18-11-2022","Movil 1",10, "https://pbs.twimg.com/media/CSQojp5UkAEVh8U.jpg").toMap())
        db.collection("matriculas")
            .add(Matricula("Juan Perone","18-11-2022","M",10, "https://pbs.twimg.com/media/CSQojp5UkAEVh8U.jpg").toMap())
        db.collection("matriculas")
            .add(Matricula("Luis Eduardo","18-11-2022","Movil 1",10, "https://dam.muyinteresante.com.mx/wp-content/uploads/2018/05/estudio-revela-que-extranos-eligen-mejores-fotos-de-perfil.jpg").toMap())
        db.collection("matriculas")
            .add(Matricula("Felipe García","18-11-2022","Mate",10, "https://pbs.twimg.com/media/CSQojp5UkAEVh8U.jpg").toMap())
        db.collection("matriculas")
            .add(Matricula("Juan Macías","18-11-2022","Movil 1",10, "https://pbs.twimg.com/media/CSQojp5UkAEVh8U.jpg").toMap())
        db.collection("matriculas")
            .add(Matricula("Abigaíl Méndez","18-11-2022","Simulación",10, "https://pbs.twimg.com/media/CSQojp5UkAEVh8U.jpg").toMap())
        db.collection("matriculas")
            .add(Matricula("Collin Hurtado","18-11-2022","Matemásticas discretas",10, "https://pbs.twimg.com/media/CSQojp5UkAEVh8U.jpg").toMap())
        db.collection("matriculas")
            .add(Matricula("Luis Macías","18-11-2022","Movil 1",10, "https://pbs.twimg.com/media/CSQojp5UkAEVh8U.jpg").toMap())

    }

    fun filtrarPorMovil1(){
        matriculas = mutableListOf()
        db.collection("matriculas")
            .whereEqualTo("materia","Movil 1")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    matriculas.add(Matricula(
                        document.data.get("estudiante").toString(),
                        document.data.get("fecha").toString(),
                        document.data.get("materia").toString(),
                        Integer.parseInt(document.data.get("cupos").toString()),
                        document.data.get("urlImagen").toString(),
                    ));
                }
                binding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = Adaptador(matriculas)
                }
            }
    }
}