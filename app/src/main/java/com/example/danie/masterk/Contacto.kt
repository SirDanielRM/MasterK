package com.example.danie.masterk

/**
 * Created by danie on 18/12/2017.
 */
class Contacto {
    var nombre: String = ""
    var telefono: String = ""

    constructor(nombre: String, telefono: String) {
        this.nombre = nombre
        this.telefono = telefono
    }
    constructor()
    constructor(arrayList: ArrayList<Contacto>)
}