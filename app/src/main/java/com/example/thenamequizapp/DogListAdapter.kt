package com.example.thenamequizapp

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class DogListAdapter(private val list: ArrayList<Dog>,
                     private val context: Context): RecyclerView.Adapter<DogListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.bindViews (list!![position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        /*
        Creates and returns a compiled view from XML file "list_row.xml"
         */
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view, context, list)
    }

    /*
    Returns the number of cards in array
     */
    override fun getItemCount(): Int {
        return list!!.size
    }



    inner class ViewHolder(itemView: View, context: Context, list:ArrayList<Dog>?): RecyclerView.ViewHolder(itemView) {
        var mContext: Context = context
        var mList: ArrayList<Dog> = list!!

        var dogName = itemView.findViewById(R.id.name_card) as TextView
        var dogImg = itemView.findViewById(R.id.img_card) as ImageView

        fun bindViews(dog: Dog){
            dogName.text = dog.name
            dogImg.setImageURI(Uri.parse(dog.img))

            itemView.setOnClickListener {
                Toast.makeText(context,"Name: ${dogName.text}" + " Path: ${dogImg.toString()}", Toast.LENGTH_LONG ).show()
            }
            itemView.setOnLongClickListener{
                var mPosition: Int = adapterPosition
                var dog: Dog = mList[mPosition]
                mList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                deleteDog(dog.id!!)
                    Toast.makeText(context, "Item: ${dogName.text} deleted", Toast.LENGTH_LONG).show()
                    true
                }
            }
            fun deleteDog(id:Int){
                var db: DogDBHelper = DogDBHelper(mContext)
                db.deleteDog(id)
            }
        }



    }


