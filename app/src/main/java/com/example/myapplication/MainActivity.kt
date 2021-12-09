package com.example.myapplication

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.LoginBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: LoginBinding

    //starts the program then loads up the login page
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.w2cprelogin)

        val dbh = DatabaseHelper(this)

        dbh.fillIngredients(this)
        dbh.fillFilters(this)



        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sets login button to open the toolbar activity when clicked
        binding.loginButton.setOnClickListener{
            startActivity(Intent(this, ToolbarActivity::class.java))
        }
    }
}