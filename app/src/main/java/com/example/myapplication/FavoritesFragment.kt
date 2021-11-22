package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Recipe.RecipeAdapter
import com.example.myapplication.Recipe.Recipedata
import com.example.myapplication.databinding.FavoritesfragmentBinding

class FavoritesFragment : Fragment(R.layout.favoritesfragment) {

    private var _binding: FavoritesfragmentBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FavoritesfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    //after view is created, this function fills in the recipe and filter recycler views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //uses function (at bottom of file) to generate list data with size 12
        val recipelist = generateRecipeList(1)

        binding.FavoritesRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            //assigner the adapter and the list that fills in the data
            adapter = RecipeAdapter(recipelist){ position -> onRecipeItemClick(position)}
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //function for generating the data that goes into the recipe
    private fun generateRecipeList(size: Int): List<Recipedata> {
        val list = ArrayList<Recipedata>()

        for (i in 0 until size) {
            val title = "carnitas"

            //insert the image first, then the title
            val item = Recipedata(R.drawable.carnitas, title)
            list += item
        }

        return list

    }

    private fun onRecipeItemClick(position: Int) {
        startActivity(Intent(activity, IndividualActivity::class.java))
    }
}
