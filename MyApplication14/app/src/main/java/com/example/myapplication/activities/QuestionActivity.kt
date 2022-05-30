package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adaptor.OptionAdaptor
import com.example.myapplication.models.Question
import com.example.myapplication.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    lateinit var mFirestore: FirebaseFirestore
    var quizlist : MutableList<Quiz>? = null
    var question : MutableMap<String,Question>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpFirestore()
        btnNext.setOnClickListener {
            index++
            setUpRecyclerView()
        }
        btnPrevious.setOnClickListener {
            index--
            setUpRecyclerView()
        }
        btnSubmit.setOnClickListener {
            Toast.makeText(this,"Final Quiz",Toast.LENGTH_LONG).show()

            val intent = Intent(this,resultActivity::class.java)
            val json = Gson().toJson(quizlist!![0])
            intent.putExtra("Quiz",json)
            startActivity(intent)
            finish()
        }




    }

    private fun setUpFirestore() {
        mFirestore = FirebaseFirestore.getInstance()
        val title = intent.getStringExtra("DATE")
        if(title != null){
        mFirestore.collection("quiz").whereEqualTo("title",title).get()
            .addOnSuccessListener {
                if(it != null && !it.isEmpty) {
                    quizlist = it.toObjects(Quiz::class.java)
                    question = quizlist!![0].question
                    setUpRecyclerView()
                    Log.d("Data", question.toString())
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        btnPrevious.visibility = View.INVISIBLE
        btnNext.visibility = View.INVISIBLE
        btnSubmit.visibility = View.INVISIBLE
        if (index == 1){
            btnNext.visibility = View.VISIBLE
        }else if (index > question!!.size){
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }else{
            btnNext.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }
        val questions = question!!["question${index}"]
        questions?.let {
            description.text = questions.description
            val optionAdaptor = OptionAdaptor(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdaptor
        }
        Log.d("Data too", question.toString())

//
    }
}