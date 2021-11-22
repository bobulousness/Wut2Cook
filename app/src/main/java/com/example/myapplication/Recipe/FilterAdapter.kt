package com.example.myapplication.Recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

//this is the adapter for the horizontal filter recycler view
class FilterAdapter(
    private val filterList: List<Filterdata>,
    private val filterListData: Array<Array<String>>,
    private val onItemClicked: (position: Int, list:Array<String>, name :String) -> Unit
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    class ViewHolder(
        view: View,
        private val filterListData: Array<Array<String>>,
        private val onItemClicked: (position:Int, Array<String>, name:String) -> Unit
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val cardView: CardView = view.findViewById(R.id.FitBit)
        val textView: TextView = view.findViewById(R.id.FilterTextView)

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v:View){
            val position = adapterPosition
            var name: String = textView.text.toString()

            var list: Array<String> = when (textView.text){
                "Difficulty" -> filterListData[0]
                "Meal Type"  -> filterListData[1]
                "Rating" -> filterListData[2]
                "Time" -> filterListData[3]
                else -> filterListData[4]
            }
            onItemClicked(position, list, name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.filterinneritem, parent, false)

        return ViewHolder(view, filterListData, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = filterList[position]
        holder.textView.text = currentItem.title
    }

    override fun getItemCount() = filterList.size

}