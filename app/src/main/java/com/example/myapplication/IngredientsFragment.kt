package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.IngredientsBinding

class IngredientsFragment: AppCompatActivity() {

    private lateinit var binding: IngredientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = IngredientsBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
    

}