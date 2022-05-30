package com.example.myapplication.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.myapplication.R
import com.example.myapplication.models.Quiz
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result.*

class resultActivity : AppCompatActivity() {

    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpView()
    }

    private fun setUpView() {
        val quizDate = intent.getStringExtra("Quiz")
        quiz = Gson().fromJson(quizDate,Quiz::class.java)
        calculateScore()
        setUpAnswer()
    }

    private fun setUpAnswer() {
        var score = 0
        for (entry in quiz.question.entries){
            val question = entry.value
            if (question.answer == question.useranswer){
                score+=10
            }
        }
        txtScore.text = "Your Score: ${score}"
    }

    private fun calculateScore() {
        val builder = StringBuilder()
        for (entry in quiz.question.entries){
            builder.append("<font color'#18206F'><b>${entry.value.description}</b></font><br/><br/>")
            builder.append("<font color'#009688'>${entry.value.useranswer}</font><br/><br/>")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(),Html.FROM_HTML_MODE_COMPACT)
        }else{
            txtAnswer.text = Html.fromHtml(builder.toString())
        }


    }

}