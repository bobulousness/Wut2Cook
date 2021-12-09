package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.myapplication.Recipe.RecipeFragment
import com.example.myapplication.databinding.MainviewBinding
import com.example.myapplication.Pantry.PantryFragment
import com.example.myapplication.Recipe.FilterDialogsFragment

class ToolbarActivity : AppCompatActivity(), FilterDialogsFragment.FilterDialogListener {

    private lateinit var binding: MainviewBinding

    private val manager = supportFragmentManager

    private val dbh = DatabaseHelper(this)

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

    override fun onDialogPositiveClick(
        dialog: DialogFragment,
        selectedItems: Array<String>,
        name: String
    ) {
        for (i in selectedItems){
            println(i)
        }
        dbh.updateUserFilters(selectedItems, "Steve", name)
    }

    fun onPantryItemClick(view: View) {

    }
}



