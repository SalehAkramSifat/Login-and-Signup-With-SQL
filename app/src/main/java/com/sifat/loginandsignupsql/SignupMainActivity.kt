package com.sifat.loginandsignupsql

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sifat.loginandsignupsql.databinding.ActivitySignupMainBinding

class SignupMainActivity : AppCompatActivity() {
    lateinit var bindind : ActivitySignupMainBinding
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivitySignupMainBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        databaseHelper = DatabaseHelper(this)

        bindind.directLogin.setOnClickListener {
            val intent1 = Intent(this, LoginMainActivity::class.java)
            startActivity(intent1)
            finish()
        }

        bindind.signup.setOnClickListener {
            val signupUsername = bindind.username.text.toString()
            val signupPassword = bindind.password.text.toString()
            signupDatabase(signupUsername, signupPassword)
        }
    }
    private fun signupDatabase(username:String, password:String){
        val inserdRowId = databaseHelper.insertUser(username, password)
        if (inserdRowId!= -1L){
            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginMainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
        }
    }
}