package com.example.myapplication

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.LoginBinding


//login activity processes credentials and takes user to
class LoginPageActivity :AppCompatActivity(){


    private lateinit var log: LoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        log = LoginBinding.inflate(layoutInflater)
        setContentView(log.root)

        val dbh = DatabaseHelper(this)
        val email = log.emailInput
        val pass = log.passwordInput

        log.button1.setOnClickListener{
            if (dbh.verify(email.text.toString(), pass.text.toString()))   {
                startActivity(Intent(this, ToolbarActivity::class.java))
            }
            else
                Toast.makeText(this,"Login Failed", Toast.LENGTH_SHORT).show()
        }

    }
}