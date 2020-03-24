package com.example.thenamequizapp


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ScoreTest {
    @get:Rule
    var activityRule = ActivityTestRule(QuizActivity::class.java)

    @Test
    fun CheckNameNotCorrect(){
        var pwView = onView(withId(R.id.txt_game_answer))
                pwView.perform(
                    click(),
                    typeText(NAME_INPUT_CHECK),
                    closeSoftKeyboard()
                )
        onView(withId(R.id.btn_enter)).perform(click())

        onView(withId(R.id.txt_score)).check(matches(withText(SCORE_CHECK)))
    }


    companion object{
        val NAME_INPUT_CHECK = "DOG_NAME_TEST"
        var SCORE_CHECK = "Your score is: 0"
    }
}