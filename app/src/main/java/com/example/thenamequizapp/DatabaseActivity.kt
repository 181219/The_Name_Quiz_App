package com.example.thenamequizapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_database.*
import java.util.*



class DatabaseActivity : AppCompatActivity() {

    private var adapter: DogListAdapter? = null
    private var dogList: MutableList<Dog>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        dogList = ArrayList()
        layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        adapter = DogListAdapter((dogList as ArrayList<Dog>), this)

        // Setup recyclerview
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        //load in data

        var dog1 = Dog()
        dog1.img = R.drawable.dogs_akita
        dog1.name = "Akita"
        dogList!!.add(dog1)
        var dog2 = Dog()
        dog2.img = R.drawable.dogs_beagle
        dog2.name = "Beagle"
        dogList!!.add(dog2)
        var dog3 = Dog()
        dog3.img = R.drawable.dogs_boxer
        dog3.name = "Boxer"
        dogList!!.add(dog3)
        var dog4 = Dog()
        dog4.img = R.drawable.dogs_chihuahua
        dog4.name = "Chihuahua"
        dogList!!.add(dog4)
        var dog5 = Dog()
        dog5.img = R.drawable.dogs_labrador
        dog5.name = "Labrador"
        dogList!!.add(dog5)
        var dog6 = Dog()
        dog6.img = R.drawable.dogs_poodle
        dog6.name = "Poodle"
        dogList!!.add(dog6)
        var dog7 = Dog()
        dog7.img = R.drawable.dogs_sheltie
        dog7.name = "Sheltie"
        dogList!!.add(dog7)

/*
        btn_add_card.setOnClickListener {
            //TODO Open a second activity where you can add a persons name and image
            var intent = Intent(this, AddingActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_PERSON)
        }
       */
    }
}