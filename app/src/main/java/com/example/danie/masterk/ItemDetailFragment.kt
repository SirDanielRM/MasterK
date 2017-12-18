package com.example.danie.masterk

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.danie.masterk.dummy.DummyContent
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    public var mItem: Contacto? = null
    var arrayContactos:ArrayList<Contacto> = ArrayList()
    lateinit var datos: DatosContactos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("nombre")

        /*if (arguments.containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP[arguments.getString(ARG_ITEM_ID)]
            mItem?.let {
                //Aqui el titulo del intent.
                activity.toolbar_layout?.title = it.content
            }
        }*/

        val menuListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                println("data change")
                for (objj in dataSnapshot.children) {
                    val registro = objj.key
                    var contact = Contacto()

                    for (dat in dataSnapshot.child(registro).children) {
                        contact.nombre = registro
                        if (dat.key == "nombre") {
                            contact.nombre = dat.value.toString()
                        }
                        if (dat.key == "telefono") {
                            contact.telefono = dat.value.toString()
                        }

                    }
                    arrayContactos?.add(contact)
                }
                for (i in arrayContactos!!.iterator()) {
                    println("nombre = " + i.nombre)
                    println("telefono = " + i.telefono)


                }
                mItem = Contacto(arrayContactos)
                if (arguments.containsKey(ARG_ITEM_ID)) {
                    // Load the dummy content specified by the fragment
                    // arguments. In a real-world scenario, use a Loader
                    // to load content from a content provider.
                    println(arguments.getString(ARG_ITEM_ID))
                    datos = DatosContactos(arrayContactos)
                    mItem = datos.getNombreList(arguments.getString(ARG_ITEM_ID))
                    mItem?.let {
                        activity.toolbar_layout?.title = it.nombre
                        /*activity..setText(it.marca)
                    activity.etMotor.setText(it.motor)
                    activity.etCombustible.setText(it.combustible)*/
                    }
                }
            }
        }
        val ref = database.reference
        ref.addValueEventListener(menuListener)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("nombre")

        /*if (arguments.containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP[arguments.getString(ARG_ITEM_ID)]
            mItem?.let {
                //Aqui el titulo del intent.
                activity.toolbar_layout?.title = it.content
            }
        }*/

        val menuListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                println("data change")
                for (objj in dataSnapshot.children) {
                    val registro = objj.key
                    var contact = Contacto()

                    for (dat in dataSnapshot.child(registro).children) {
                        contact.nombre = registro
                        if (dat.key == "nombre") {
                            contact.nombre = dat.value.toString()
                        }
                        if (dat.key == "telefono") {
                            contact.telefono = dat.value.toString()
                        }

                    }
                    arrayContactos?.add(contact)
                }
                for (i in arrayContactos!!.iterator()) {
                    println("nombre = " + i.nombre)
                    println("telefono = " + i.telefono)


                }
                mItem = Contacto(arrayContactos)
                if (arguments.containsKey(ARG_ITEM_ID)) {
                    // Load the dummy content specified by the fragment
                    // arguments. In a real-world scenario, use a Loader
                    // to load content from a content provider.
                    println(arguments.getString(ARG_ITEM_ID))
                    datos = DatosContactos(arrayContactos)
                    mItem = datos.getNombreList(arguments.getString(ARG_ITEM_ID))
                    mItem?.let {
                        activity.toolbar_layout?.title = it.nombre
                    }
                    mItem?.let {
                        rootView.item_detail.text = "Telefono " + it.telefono

                    }
                }
            }
        }
        val ref = database.reference
        ref.addValueEventListener(menuListener)
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
