package com.example.myapplication.Pantry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.PantryfragmentBinding
import java.io.BufferedReader
import java.io.File
import java.io.InputStream

//this fragment hosts the page where users input ingredients
class PantryFragment : Fragment(R.layout.pantryfragment) {

    private var _binding: PantryfragmentBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        _binding = PantryfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = generatePantryList(8)

        binding.PantryRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = PantryAdapter(list)
        }
    }

    private fun generatePantryList(size: Int): List<Pantrydata> {

         val inputStream: BufferedReader = requireActivity().assets.open("ingredients.txt").bufferedReader()
         val lineList = mutableListOf<String>()
         val list = ArrayList<Pantrydata>()

        inputStream.useLines { lines -> lines.forEach { lineList.add(it)} }
        lineList.forEach{
            list += Pantrydata(it)
        }

        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}