package com.example.danie.masterk.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, DummyItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
       // for (i in 1..COUNT) {

            addItem(DummyItem("1", "Jose Cordoba", "Profesor de AD y de PDM\n ", "958123456"))
            addItem(DummyItem("2", "Luis Sanchez", "Profesor de SI y de SGE", "958123456"))
            addItem(DummyItem("3", "Silvestre Martinez", "Profesor de PSP", "958123456"))
            addItem(DummyItem("4", "Jose Manuel Aranda", "Profesor de Empresas e Iniciativa Emprendedora", "958123456"))
            //addItem(createDummyItem(6))
        //}
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): DummyItem {
        return DummyItem(position.toString(), "Pos " + position, makeDetails(position), createTelef(position) )
    }
    private fun createTelef(position: Int):String{
        val builder = StringBuilder()
        builder.append("958345103")
        return builder.toString()
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Posicion en la lista: ").append(position)

        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: String, val content: String, val details: String, val telef: String) {
        override fun toString(): String = content
    }
}
