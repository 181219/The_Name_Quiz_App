package com.example.thenamequizapp

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    var name: String = ""
    var img: Int = 0
    var isCorrect: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        var data = intent.extras

        if(data != null){
            name = data.get("name").toString().toLowerCase()
            img = data.getInt("img")
            var score: Int = data.getInt("score")
            img_game.setImageResource(img)
            txt_score.text = "Your score is: " +score.toString()
        }

        btn_enter.setOnClickListener{
           sendAnswer()
        }

        findViewById<EditText>(R.id.txt_game_answer).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    sendAnswer()
                    true
                }
                else -> false
            }
        }

    }
    fun sendAnswer(){
        var returnInt = this.intent

        var answer: String = txt_game_answer.text.toString().toLowerCase()
        if (answer==name){
            isCorrect=true
            Toast.makeText(this, "CORRECT!", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "WRONG! The answer was ${name}", Toast.LENGTH_LONG).show()
        }
        returnInt.putExtra("correct", isCorrect)
        setResult(Activity.RESULT_OK, returnInt)
        finish()
    }
}
