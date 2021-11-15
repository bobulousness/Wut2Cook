package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.databinding.MainviewBinding

class ToolbarActivity : AppCompatActivity() {

    private lateinit var binding: MainviewBinding

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pantryButton.setOnClickListener{
            val transaction = manager.beginTransaction()
            val fragment = PantryFragment()
            transaction.replace(R.id.MainFragmentView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.toolbarSearchButton.setOnClickListener{
            val transaction = manager.beginTransaction()
            val fragment = RecipeFragment()
            transaction.replace(R.id.MainFragmentView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}



