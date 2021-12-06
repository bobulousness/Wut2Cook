package com.example.myapplication.pantry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.PantryfragmentBinding
import java.io.BufferedReader

//this fragment hosts the page where users input ingredients
class PantryFragment : Fragment(R.layout.pantryfragment) {

    private var _binding: PantryfragmentBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {




        _binding = PantryfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = generatePantryList()

        binding.PantryRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = PantryAdapter(list)
        }
    }

    private fun generatePantryList(): List<Pantrydata> {

        val inputStream: BufferedReader = requireActivity().assets.open("ingredients.txt").bufferedReader()
        val lineList = mutableListOf<String>()

        var set = false
        val meatList = ArrayList<Pantrydata>()


        inputStream.useLines { lines -> lines.forEach { lineList.add(it)} }
        lineList.forEach{
            /*if(it == "-"){
                set = true
            }
            if (set == true){
                if (it == "meat")
            }*/

            meatList += Pantrydata(it,false)
        }

        return meatList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}