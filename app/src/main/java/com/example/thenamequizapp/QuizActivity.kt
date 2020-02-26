package com.example.thenamequizapp

import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*

class QuizActivity : AppCompatActivity() {

    var name: String = ""
    var img: String? = null
    var dbHandler: DogDBHelper? = null
    var score: Int = 0
    private var quizList: ArrayList<Dog>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        dbHandler = DogDBHelper(this)
        quizList = ArrayList()
        quizList = dbHandler!!.readDogs()
        txt_score.text = "Your score is: " +score.toString()
        showRandomQuizDog()

        btn_enter.setOnClickListener{
           checkAnswer()
        }


        // Edit Listener
        findViewById<EditText>(R.id.txt_game_answer).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    true
                }
                else -> false
            }
        }

    }
    fun checkAnswer(){

        var answer: String = txt_game_answer.text.toString().toLowerCase()
        if (answer==name){
            score++
            Toast.makeText(this, "CORRECT!", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "WRONG! The answer was ${name}", Toast.LENGTH_LONG).show()
        }
       // returnInt.putExtra("correct", isCorrect)
        //setResult(Activity.RESULT_OK, returnInt)
        txt_game_answer.setText("")
       showRandomQuizDog()
    }

    fun showRandomQuizDog(){

        //Get a random dog from list
        var randDog: Dog = quizList!![randomN()]

        //Sett random dog as quiz question
        name = randDog.name.toString().toLowerCase()
        img = randDog.img.toString()
        img_game.setImageURI(Uri.parse(img))
        txt_score.text = "Your score is: " +score.toString()
    }

    fun randomN(): Int{
        var doggyCount = dbHandler!!.getDogsCount()
        var rand = Random().nextInt(doggyCount)
       return rand
    }

}
