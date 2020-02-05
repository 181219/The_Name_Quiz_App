package com.example.thenamequizapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    val REQUEST_CODE_DOG: Int = 1
    val REQUEST_CODE_GAME: Int = 2
    var score: Int = 0
    var correct: Boolean = false
    private var adapter: DogListAdapter? = null
    private var dogList: MutableList<Dog>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        btn_add_card.setOnClickListener {
            var intent = Intent(this, AddingActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_DOG)
        }

        btn_start_quiz.setOnClickListener {
            var intent = Intent(this, QuizActivity::class.java)

            var number = randomN()
            //Picks a random dog from dogList and sends it to quiz activity
            intent.putExtra("name", dogList!![number].name)
            intent.putExtra("img", dogList!![number].img)
            intent.putExtra("score", score)

            startActivityForResult(intent, REQUEST_CODE_GAME)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DOG) {
            if (resultCode == Activity.RESULT_OK) {
                var dog = Dog()
                dog.name = data!!.extras.get("name").toString()
                dog.img = data!!.extras.getInt("img")

//                person.img = data!!.extras.get("img").toString().toInt()
                dogList!!.add(dog)
                Toast.makeText(this, dog.name + " " + dog.img, Toast.LENGTH_LONG).show()
                /**
                 * Notifies changes, updates the view
                 */
                adapter!!.notifyDataSetChanged()
            }
        }

        if (requestCode == REQUEST_CODE_GAME) {
            if (resultCode == Activity.RESULT_OK) {
                correct = data!!.extras.getBoolean("correct")
                score(correct)
            }
            backToQuiz()
        }
    }

    fun randomN(): Int {
        var random = Random()
        return random.nextInt(dogList!!.size)
    }

    fun score(correct: Boolean): Int {
        if (correct) {
            score++
        }
        return score
    }

    fun backToQuiz(){
        var intent = Intent(this, QuizActivity::class.java)

        var number = randomN()
        //Picks a random dog from dogList and sends it to quiz activity
        intent.putExtra("name", dogList!![number].name)
        intent.putExtra("img", dogList!![number].img)
        intent.putExtra("score", score)

        startActivityForResult(intent, REQUEST_CODE_GAME)
        this.overridePendingTransition(0, 0)
    }
}

