package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adaptor.QuizAdaptor
import com.example.myapplication.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var mfirestore: FirebaseFirestore
    lateinit var quizAdaptor: QuizAdaptor
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    var quizlist = mutableListOf<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpDrawerLayout()
        setUpRecyclerView()
        getDataFromFirebaseFireStore()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        date_picker.setOnClickListener {
            val date = MaterialDatePicker.Builder.datePicker().build()
            date.show(supportFragmentManager,"DatePicker")
            date.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.timeInMillis = it
                val simpleDate = SimpleDateFormat("yyyy/MM/dd")
                val formattedDate: String = simpleDate.format(calendar.time)
                Log.d("DATE",formattedDate)
                val intent= Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE",formattedDate)
                startActivity(intent)
            }
            date.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER",date.headerText)
            }
            date.addOnCancelListener {
                Log.d("DATEPICKER","DatePicker Canceled")
            }

        }
    }

    private fun getDataFromFirebaseFireStore() {
        mfirestore = FirebaseFirestore.getInstance()
        mfirestore.collection("quiz").addSnapshotListener { value, error ->
            if (value== null || error != null){
                Toast.makeText(this,"Error in Fetching Data",Toast.LENGTH_LONG).show()
            }
            quizlist.clear()
            quizlist.addAll(value!!.toObjects(Quiz::class.java))
            quizAdaptor.notifyDataSetChanged()


            Log.d("Data Yaseen", value!!.toObjects(Quiz::class.java).toString())
        }
    }


    private fun setUpRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this,2)
        quizAdaptor = QuizAdaptor(this,quizlist)
        recyclerView.adapter = quizAdaptor
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(topAppBar)

        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawer_Main,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}