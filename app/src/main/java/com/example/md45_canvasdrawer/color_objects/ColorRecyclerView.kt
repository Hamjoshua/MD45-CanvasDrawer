package com.example.md45_canvasdrawer.color_objects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.md45_canvasdrawer.R


class ColorViewHolder(view: View) : ViewHolder(view) {
    val colorButton = view.findViewById<Button>(R.id.colorBtn)
}
class ColorRecyclerView : ListAdapter<ColorDataClass, ColorViewHolder>(ColorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.r_item_color, parent,
            false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val colorDC = currentList[position]
        holder.colorButton.setText(colorDC.name)
        holder.colorButton.setBackgroundColor(colorDC.color)
    }
}

class ColorDiffCallback : DiffUtil.ItemCallback<ColorDataClass>() {
    override fun areItemsTheSame(oldItem: ColorDataClass, newItem: ColorDataClass): Boolean {
        return oldItem.color == newItem.color;
    }

    override fun areContentsTheSame(oldItem: ColorDataClass, newItem: ColorDataClass): Boolean {
        return oldItem == newItem;
    }
}