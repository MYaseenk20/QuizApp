 package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

 class Login : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }

        btnSignUp.setOnClickListener {
            val intent =  Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login() {
        val emailtxt = etEmailAddress.text.toString().trim()
        val passwordtext = etPassword.text.toString()

        firebaseAuth.signInWithEmailAndPassword(emailtxt,passwordtext)
            .addOnCompleteListener(this) {
                if (it.isSuccessful){
                    Toast.makeText(this,"SuccessFull",Toast.LENGTH_LONG).show()
                    val intent =  Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Not SuccessFull",Toast.LENGTH_LONG).show()
                }
            }


    }
}