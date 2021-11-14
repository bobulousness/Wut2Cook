package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.RecipepageBinding

class RecipeFragment : Fragment(R.layout.recipepage) {

    private var _binding: RecipepageBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState:Bundle?
    ): View? {

        _binding = RecipepageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
       super.onViewCreated(view, savedInstanceState)
        binding.RecipeRecyclerView.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = RecipeAdapter()

        }
   }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }


}