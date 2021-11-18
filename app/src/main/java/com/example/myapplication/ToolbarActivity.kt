package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Recipe.RecipeFragment
import com.example.myapplication.databinding.MainviewBinding
import com.example.myapplication.Pantry.PantryFragment

class ToolbarActivity : AppCompatActivity() {

    private lateinit var binding: MainviewBinding

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sets the pantry button to switch the fragment view (MainFragmentView) to the pantry fragment
        binding.pantryButton.setOnClickListener{
            val transaction = manager.beginTransaction()
            val fragment = PantryFragment()
            transaction.replace(R.id.MainFragmentView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //sets the search button on the toolbar to switch the fragment view (MainFragmentView) to the recipe fragment
        binding.ToolbarSearchButton.setOnClickListener{
            val transaction = manager.beginTransaction()
            val fragment = RecipeFragment()
            transaction.replace(R.id.MainFragmentView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //sets the favorites button to switch the fragment view to the favorites fragment
        binding.ToolbarFavoritesButton.setOnClickListener{
            val transaction = manager.beginTransaction()
            val fragment = FavoritesFragment()
            transaction.replace(R.id.MainFragmentView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}



