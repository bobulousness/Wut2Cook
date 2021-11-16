package com.example.myapplication.Recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.RecipefragmentBinding


//this is the fragment with the filters and recipes and search bar on it
class RecipeFragment : Fragment(R.layout.recipefragment) {

    private var _binding: RecipefragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState:Bundle?
    ): View? {

        _binding = RecipefragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    //after view is created, this function fills in the recipe and filter recycler views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
       super.onViewCreated(view, savedInstanceState)

        //uses function (at bottom of file) to generate list data with size 12
        val recipelist = generateRecipeList(12)

        binding.RecipeRecyclerView.apply{
            layoutManager = LinearLayoutManager(activity)
            //assigner the adapter and the list that fills in the data
            adapter = RecipeAdapter(recipelist)
        }

        binding.FilterRecyclerView.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = FilterAdapter(generateFilterList(12))
        }
   }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }

    //function for generating the data that goes into the recipe
    private fun generateRecipeList(size: Int): List<recipedata>{
        val list = ArrayList<recipedata>()

        for (i in 0 until size) {
            val title = when (i % 3){
                0-> "generated title 1"
                1-> "generated title 2"
                else -> "generated title 3"
            }

            //insert the image first, then the title
            val item = recipedata(R.drawable.logo2, title)
            list += item
        }

        return list

    }

    //function for generating filter data
    private fun generateFilterList(size: Int): List<Filterdata>{
        var list = ArrayList<Filterdata>()

        val filter1 = Filterdata("Meal type")
        val filter2 = Filterdata("Diet")
        val filter3 = Filterdata("Main Ingredient")

        list += filter1
        list += filter2
        list += filter3

        return list
    }
}