package com.example.thenamequizapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {
    val REQUEST_CODE_DOG: Int = 1
    val REQUEST_CODE_GAME: Int = 2
    val REQUEST_CODE_DATAB: Int = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var mAdView : AdView

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



        // setup preference
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {
            val owner = getString("OWNER", "")
            owner_text.setText(owner)
        }

        //Edit Listener
        findViewById<EditText>(R.id.owner_text).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {

                    saveData()
                    true
                }
                else -> false
            }
        }


        btn_DB.setOnClickListener{
            var intent = Intent(this, DatabaseActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_DATAB)
        }


        btn_add_card.setOnClickListener {
            var intent = Intent(this, AddingActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_DOG)
        }

        btn_start_quiz.setOnClickListener {
            var intent = Intent(this, QuizActivity::class.java)
/*
            var number = randomN()
            //Picks a random dog from dogList and sends it to quiz activity
            intent.putExtra("name", dogList!![number].name)
            intent.putExtra("img", dogList!![number].img)
            intent.putExtra("score", score)
*/
            startActivityForResult(intent, REQUEST_CODE_GAME)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DOG) {
            if (resultCode == Activity.RESULT_OK) {
                /* var name = data!!.extras.get("name").toString()
                val imgPath: String = data!!.extras.get("img").toString()
                var img = (imgPath)

                var dog = Dog()
                dog.name = name
                dog.img = img
//                dogList!!.add(dog)
                adapter!!.addItem(dog)
                Toast.makeText(this, name + " " + img, Toast.LENGTH_LONG).show()
                /**
                 * Notifies changes, updates the view
                 */
                adapter!!.notifyDataSetChanged()*/
            }
        }

        if (requestCode == REQUEST_CODE_GAME) {
            if (resultCode == Activity.RESULT_OK) {
                //  correct = data!!.extras.getBoolean("correct")
                // score(correct)
            }
            //backToQuiz()
        }

        if (requestCode == REQUEST_CODE_DATAB) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "ALL GOOD WERE BACK", Toast.LENGTH_LONG).show()
            }
        }
    }
        /*
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
*/
        /* fun checkOwnerName(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        if
        val ownerName: String = pref.getString("owner_name", null) // getting String

    }
*/
        fun saveData() {
            val pref = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = pref.edit()

            editor
                .putString("OWNER", owner_text.text.toString())
                .apply()

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

        }
/*    fun checkDB(){
        val doggyCount = dbHandler!!.getDogsCount()
        if(doggyCount ==0){
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
    }*/
    }


