package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.RecipefragmentBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
       super.onViewCreated(view, savedInstanceState)
        binding.RecipeRecyclerView.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = RecipeAdapter()
        }

        binding.FilterRecyclerView.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = FilterAdapter()
        }
   }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }
}