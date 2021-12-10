package com.example.myapplication

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.LoginBinding
import com.example.myapplication.databinding.RegisterBinding
import com.example.myapplication.databinding.W2cpreloginBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding: W2cpreloginBinding
    //starts the program then loads up the login page
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.w2cprelogin)

        binding = W2cpreloginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //sets login and register button to open the respected activity when clicked
        binding.login.setOnClickListener{
            startActivity(Intent(this, LoginPageActivity::class.java))
        }

        binding.register.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}