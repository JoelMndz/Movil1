package com.example.examenpreciado

class Matricula(var estudiante: String,
                var fecha: String,
                var materia: String,
                var cupos:Int,
                var urlImagen: String){

    fun toMap():HashMap<String, Any>{
        return hashMapOf(
            "estudiante" to estudiante,
            "fecha" to fecha,
            "materia" to materia,
            "cupos" to cupos,
            "urlImagen" to urlImagen
        )
    }
}