package com.sifat.loginandsignupsql

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sifat.loginandsignupsql.databinding.ActivityLoginMainBinding

class LoginMainActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginMainBinding
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseHelper = DatabaseHelper(this)

        binding.sign.setOnClickListener {
            val intent1 = Intent(this, SignupMainActivity::class.java)
            startActivity(intent1)
            finish()
        }
        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            loginDatabseUser(username, password)

        }

    }

    private fun loginDatabseUser(username: String, password: String) {
        try {
            val userExists = databaseHelper.readUser(username, password)
            if (userExists) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}