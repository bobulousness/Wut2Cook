package com.example.myapplication.Pantry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.PantryfragmentBinding
import java.io.BufferedReader
import android.widget.CheckBox

//this fragment hosts the page where users input ingredients
class PantryFragment : Fragment(R.layout.pantryfragment) {

    private var _binding: PantryfragmentBinding? = null

    private val binding get() = _binding!!

    var pantryList: List<Pantrydata> = emptyList()

    var templist = List<Pantrydata>(96){ Pantrydata("",false) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = PantryfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pantryList = generatePantryList()

        binding.PantryRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = PantryAdapter(pantryList)
        }
    }

    private fun generatePantryList(): List<Pantrydata> {

        val inputStream: BufferedReader =
            requireActivity().assets.open("ingredients.txt").bufferedReader()
        val lineList = mutableListOf<String>()

        var set = false
        val list = ArrayList<Pantrydata>()
        var i = 0


        inputStream.useLines { lines -> lines.forEach { lineList.add(it) } }
        lineList.forEach {
            when {
                it == "-" -> {set = true; i++}
                set -> {set = false; i++}
                else -> {list += Pantrydata(it, templist[i].on);  i++}
            }
        }

        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}