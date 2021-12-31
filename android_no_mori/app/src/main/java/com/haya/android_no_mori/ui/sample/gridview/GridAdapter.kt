package com.haya.android_no_mori.ui.sample.gridview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haya.android_no_mori.R
import com.haya.android_no_mori.ui.sample.gridview.model.Animal

class GridAdapter(private val animalList: List<Animal>) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_view)
        val name: TextView = view.findViewById(R.id.text_view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val animal = animalList[position]
        viewHolder.image.setImageResource(animal.image)
        viewHolder.name.text = animal.name
    }

    override fun getItemCount() = animalList.size
}