package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.MainviewBinding

class ToolbarActivity : AppCompatActivity() {

    private lateinit var binding: MainviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}