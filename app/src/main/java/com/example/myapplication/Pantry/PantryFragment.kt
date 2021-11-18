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

        val list = ArrayList<Pantrydata>()

        for (i in 0 until 8) {
            val title = when (i % 3) {
                0 -> Pantrydata("generated title 1")
                1 -> Pantrydata("title 2")
                else -> Pantrydata("gen title 3")
            }
            list += title
        }
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}