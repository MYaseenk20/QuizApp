package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.btnSignUp
import kotlinx.android.synthetic.main.activity_signup.etEmailAddress
import kotlinx.android.synthetic.main.activity_signup.etPassword

class SignupActivity : AppCompatActivity() {
    lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            signUp()
        }

        btnLogin.setOnClickListener {
            val intent =  Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUp() {
        val emailtxt = etEmailAddress.text.toString().trim();
        val passwordtxt = etPassword.text.toString()
        val C_passwordtxt = etConfirmPassword.text.toString()

        if (emailtxt.isEmpty() || passwordtxt.isEmpty() || C_passwordtxt.isEmpty()){
            Toast.makeText(this,"Kindly Filled All Field",Toast.LENGTH_LONG).show()
            return
        }

        if (passwordtxt != C_passwordtxt){
            Toast.makeText(this,"Password and Confirm Password do not match",Toast.LENGTH_LONG).show()
            return
        }


        firebaseAuth.createUserWithEmailAndPassword(emailtxt,passwordtxt)
            .addOnCompleteListener(this) {
                if(it.isSuccessful){
                    Toast.makeText(this,"SuccessFull",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
    }
}