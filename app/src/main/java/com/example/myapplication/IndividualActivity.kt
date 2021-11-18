package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.IndividualactivityBinding

class IndividualActivity() : AppCompatActivity(){

    private lateinit var binding: IndividualactivityBinding

    //starts the program then loads up the login page
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = IndividualactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sets login button to open the toolbar activity when clicked
        binding.Leave.setOnClickListener{
            startActivity(Intent(this, ToolbarActivity::class.java))
        }
    }
}