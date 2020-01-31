package com.example.thenamequizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //function called when user clicks on buttons in main menu
    fun goDatabase(view: View){
        //go to database activity
        val goToDatabase = Intent(this, DatabaseActivity::class.java).apply{}
        startActivity(goToDatabase);
    }

    fun goQuiz(view: View){
        //go to quiz activity
        val goToQuiz = Intent(this, QuizActivity::class.java).apply{}
        startActivity(goToQuiz);
    }

    fun goAdd(view: View){
        //go to add activity
        //val goToAdd = Intent(this, AddActivity::class.java).apply{}
        //startActivity(goToAdd);
    }
}
