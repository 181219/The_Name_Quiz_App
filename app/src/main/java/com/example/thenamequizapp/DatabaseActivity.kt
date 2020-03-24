package com.example.thenamequizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_database.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class DatabaseActivity : AppCompatActivity() {

    private var adapter: DogListAdapter? = null
    private var dogList: ArrayList<Dog>? = null
    private var dogListItems: ArrayList<Dog>? = null

    private var layoutManager: RecyclerView.LayoutManager? = null
    var dbHandler: DogDBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        dbHandler = DogDBHelper(this)

        dogList = ArrayList()
        dogListItems = ArrayList()
        layoutManager = LinearLayoutManager(this)
        adapter = DogListAdapter(dogListItems!!, this)

        // Setup recyclerview
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        checkDB()

        dogList = dbHandler!!.readDogs()

        //dogList!!.reverse()

        for (d in dogList!!.iterator()){
            val dog = Dog()

            dog.name = "Name: ${d.name}"
            dog.img = d.img
            dog.id = d.id

            dogListItems!!.add(dog)
        }

        adapter!!.notifyDataSetChanged()


    }

    fun checkDB(){
        if(dbHandler!!.getDogsCount() ==0){
            addTestDogs()
        }
    }

    fun addTestDogs(){
        var dog1 = Dog()
        dog1.img = "android.resource://com.example.thenamequizapp/drawable/dogs_akita"
        dog1.name = "Akita"
        dbHandler!!.createDog(dog1)
        var dog2 = Dog()
        dog2.img = "android.resource://com.example.thenamequizapp/drawable/dogs_beagle"
        dog2.name = "Beagle"
        dbHandler!!.createDog(dog2)
        var dog3 = Dog()
        dog3.img = "android.resource://com.example.thenamequizapp/drawable/dogs_boxer"
        dog3.name = "Boxer"
        dbHandler!!.createDog(dog3)
    }
}
