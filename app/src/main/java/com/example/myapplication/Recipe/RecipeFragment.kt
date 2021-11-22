package com.example.myapplication.Recipe

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.IndividualActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.RecipefragmentBinding
import java.io.BufferedReader


//this is the fragment with the filters and recipes and search bar on it
class RecipeFragment : Fragment(R.layout.recipefragment) {

    private var _binding: RecipefragmentBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecipefragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    //after view is created, this function fills in the recipe and filter recycler views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //uses function (at bottom of file) to generate list data with size 12
        val recipelist = generateRecipeList(12)

        val filterArray = readFilterData()

        binding.RecipeRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            //assigner the adapter and the list that fills in the data
            adapter = RecipeAdapter(recipelist){ position -> onRecipeItemClick(position)}
        }

        binding.FilterRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = FilterAdapter(generateFilterList(3), filterArray){ position, list -> onFilterItemClick(position, filterArray!![0] )}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //function for generating the data that goes into the recipe
    private fun generateRecipeList(size: Int): List<Recipedata> {
        val list = ArrayList<Recipedata>()

        val title = "carnitas"
        val item = Recipedata(R.drawable.carnitas, title)
        list += item

        for (i in 0 until size) {
            val title = when (i % 3) {
                0 -> "generated title 1"
                1 -> "generated title 2"
                else -> "generated title 3"
            }

            //insert the image first, then the title
            val item = Recipedata(R.drawable.logo2, title)
            list += item
        }

        return list

    }

    //function for generating filter data
    private fun generateFilterList(size: Int): List<Filterdata> {
        var list = ArrayList<Filterdata>()

        val filter1 = Filterdata("Main Ingredient")
        val filter2 = Filterdata("Meal Type")
        val filter3 = Filterdata("Rating")
        val filter4 = Filterdata("Difficulty")
        val filter5 = Filterdata("Time")


        list += filter4
        list += filter2
        list += filter3
        list += filter5
        list += filter1

        return list
    }

    private fun readFilterData(): Array<Array<String>>{
        var filterList: Array<Array<String>> = emptyArray()
        var filter: MutableList<String> = mutableListOf()

        val inputStream: BufferedReader = requireActivity().assets.open("time.txt").bufferedReader()
        val lineList = mutableListOf<String>()

        var set = true

        val filenames = arrayOf("difficulty.txt","mealtype.txt","rating.txt","time.txt")

        for(i in filenames){
            filterList += textToArray(i)
        }

        inputStream.useLines { lines -> lines.forEach { lineList.add(it)} }
        lineList.forEach{

            if(it == "-"){
                set = true
            } else if (set){
                set = false
            } else {
                filter += it
            }
        }

        filterList += filter.toTypedArray()

        return filterList
    }

    private fun textToArray(fileName: String): Array<String>{
        val inputStream: BufferedReader = requireActivity().assets.open(fileName).bufferedReader()
        val lineList = mutableListOf<String>()

        var list: Array<String> = emptyArray()

        inputStream.useLines { lines -> lines.forEach { lineList.add(it)} }
        lineList.forEach {
            list += it
        }
        return list
    }

    private fun onRecipeItemClick(position: Int) {
        startActivity(Intent(activity, IndividualActivity::class.java))
    }

    private fun onFilterItemClick(position: Int, content: Array<String>) {
        FilterDialogsFragment(content).show(childFragmentManager, "neato")
    }
}