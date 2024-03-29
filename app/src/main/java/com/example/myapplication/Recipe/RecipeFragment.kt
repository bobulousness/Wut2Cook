package com.example.myapplication.Recipe

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.DatabaseHelper
import com.example.myapplication.IndividualActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.RecipefragmentBinding
import java.io.BufferedReader


//this is the fragment with the filters and recipes and search bar on it
class RecipeFragment : Fragment(R.layout.recipefragment){

    private var _binding: RecipefragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecipefragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    //after view is created, this function fills in the recipe and filter recycler views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbh = DatabaseHelper(requireActivity())

        //uses function (at bottom of file) to generate list data with size 12
        val recipelist = generateRecipeList(12)
        val filterTitle = generateFilterList()
        var filterArray: Array<Array<FilterItemdata>> = emptyArray()

        for (i in filterTitle){
            filterArray += dbh.getFilters(i)
        }

        // binding the "recipeRecyclerView" from recipefragment.xml to the recipe adapter
        // passing it the function to use for an on click event: "onRecipeItemClick"
        binding.RecipeRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            //assigner the adapter and the list that fills in the data
            adapter = RecipeAdapter(recipelist){onRecipeItemClick()}
        }

        binding.FilterRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = FilterAdapter(filterTitle, filterArray){list, name -> onFilterItemClick(list, name)}
        }
    }

    //what happens when the view is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //function for generating the data that goes into the recipe
    private fun generateRecipeList(size: Int): List<Recipedata> {
        val list = ArrayList<Recipedata>()

        var title = "carnitas"
        val item = Recipedata(R.drawable.carnitas, title)
        list += item

        for (i in 0 until size) {
            title = when (i % 3) {
                0 -> "generated title 1"
                1 -> "generated title 2"
                else -> "generated title 3"
            }

            //insert the image first, then the title

            list += Recipedata(R.drawable.logo2, title)
        }

        return list

    }

    //function for generating a list of the names for eahc filter
    private fun generateFilterList(): List<String> {
        val list = ArrayList<String>()

        list += "Difficulty"
        list += "Meal Type"
        list += "Rating"
        list += "Time"
        list += "Main Ingredient"

        return list
    }

    //handles creating arrays for each set of filters
    private fun readFilterData(): Array<Array<String>>{
        var filterList: Array<Array<String>> = emptyArray()
        val filter: MutableList<String> = mutableListOf()

        var set = true
        val filenames = arrayOf("difficulty.txt","mealtype.txt","rating.txt","time.txt")

        for(i in filenames){
            filterList += textToArray(i)
        }

        val inputStream: BufferedReader = requireActivity().assets.open("ingredients.txt").bufferedReader()
        val lineList = mutableListOf<String>()

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

    //feeds lines of a text file into an array
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

    //function that handles when the recipe cards are clicked
    private fun onRecipeItemClick() {
        startActivity(Intent(activity, IndividualActivity::class.java))
    }

    //function that stores which filter options have been selected
    private fun onFilterItemClick(content: Array<FilterItemdata>, name: String) {

        val dbh = DatabaseHelper(requireActivity())

        val list = dbh.getUserFilters("Steve", name)

        var feed: Array<String> = emptyArray()
        var selectedItems = booleanArrayOf()

        for(i in content){
            feed += i.title
            selectedItems += list.contains(i.title)

        }

        FilterDialogsFragment(feed, name, selectedItems, list).show(childFragmentManager, "FilterDialogFragment")
    }



}