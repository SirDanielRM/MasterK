package com.example.danie.masterk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.danie.masterk.dummy.DummyContent
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*

import kotlinx.android.synthetic.main.item_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false

    public var mItem: Contacto? = null
    var arrayContactos:ArrayList<Contacto> = ArrayList()
    lateinit var datos: DatosContactos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        val database = FirebaseDatabase.getInstance()
        //val myRef = database.getReference("masterk-daef9")
        //println(myRef.key)
        setSupportActionBar(toolbar)
        toolbar.title = title


        datos = DatosContactos(arrayContactos)
        setupRecyclerView(item_list)

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

                datos = DatosContactos(arrayContactos)
                setupRecyclerView(item_list)
            }
        }

        val ref = database.reference
        ref.addValueEventListener(menuListener)

    }
    private fun setupRecyclerView(recyclerView: RecyclerView) {
        datos = DatosContactos(arrayContactos)
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, datos.getLista(), mTwoPane)
    }

    class SimpleItemRecyclerViewAdapter(private val mParentActivity: ItemListActivity,
                                        private val mValues: MutableList<Contacto>,
                                        private val mTwoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val mOnClickListener: View.OnClickListener

        init {
            mOnClickListener = View.OnClickListener { v ->
                val item = v.tag as Contacto
                if (mTwoPane) {
                    val fragment = ItemDetailFragment().apply {
                        arguments = Bundle()
                        arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.nombre)
                    }
                    mParentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                        putExtra(ItemDetailFragment.ARG_ITEM_ID, item.nombre)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mValues[position]
            holder.mIdView.text = item.nombre
            //holder.mContentView.text = item.content

            with(holder.itemView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            val mIdView: TextView = mView.id_text
            val mContentView: TextView = mView.content
        }
    }
}
