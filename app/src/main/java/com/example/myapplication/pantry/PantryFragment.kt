package com.example.myapplication.pantry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.PantryfragmentBinding

//this fragment hosts the page where users input ingredients
class PantryFragment: Fragment(R.layout.pantryfragment) {

    private var _binding: PantryfragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState:Bundle?
    ): View? {
        _binding = PantryfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }
}