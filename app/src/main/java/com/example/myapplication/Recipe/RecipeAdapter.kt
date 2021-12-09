package com.example.myapplication.Recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


//this is the adapter for the vertical recipe recycler view
class RecipeAdapter(
    private val recipeList: List<Recipedata>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>()
{

    class ViewHolder(
        view: View,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val imageView: ImageView = view.findViewById(R.id.recipeimage)
        val titleView: TextView = view.findViewById(R.id.recipetitle)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipeitem, parent, false)

        return ViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = recipeList[position]

        //changes the recipe image
        holder.imageView.setImageResource(currentItem.imageResource)

        //changes the recipe title
        holder.titleView.text = currentItem.title
    }

    override fun getItemCount() = recipeList.size

}