package com.example.thenamequizapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class DogListAdapter(private val list: ArrayList<Dog>,
                     private val context: Context): RecyclerView.Adapter<DogListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): DogListAdapter.ViewHolder {
        /*
        Creates and returns a compiled view from XML file "list_row.xml"
         */
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    /*
    Returns the number of cards in array
     */
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DogListAdapter.ViewHolder, position: Int) {
        holder!!.bindItem(list[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(dog: Dog){
            var name: TextView = itemView.findViewById(R.id.name_card) as TextView
            var img: ImageView = itemView.findViewById(R.id.img_card) as ImageView


            name.text = dog.name
            img.setImageResource(dog.img!!)

            itemView.setOnClickListener {
                Toast.makeText(context,"Name: ${name.text}", Toast.LENGTH_LONG ).show()
            }
            itemView.setOnLongClickListener{
                    removeAt(adapterPosition)
                    Toast.makeText(context, "Item: ${name.text} deleted", Toast.LENGTH_LONG).show()
                    true
                }
            }

        }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }

    }


