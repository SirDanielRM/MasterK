package com.example.danie.masterk

/**
 * Created by danie on 18/12/2017.
 */
class DatosContactos {
    public var arrayContactos: MutableList<Contacto> = ArrayList()

    constructor(arrayContactos: MutableList<Contacto>){
        this.arrayContactos=arrayContactos

    }
    public fun getLista():MutableList<Contacto>{
        return arrayContactos
    }
    public fun getNombreList(nom: String):Contacto{
        for (ob in arrayContactos) {
            if (ob.nombre == nom) {
                return ob
            }
        }
        return arrayContactos.get(0)
    }

}