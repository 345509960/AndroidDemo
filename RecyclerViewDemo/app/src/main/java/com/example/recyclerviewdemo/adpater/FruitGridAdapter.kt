package com.example.recyclerviewdemo.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.model.Fruit
import com.example.recyclerviewdemo.R

/**
 * create by lyc
 * on 2019/10/30
 * 描述 水果展示适配器-GridLayout
 */
class FruitGridAdapter(private val mFruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitGridAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fruitImage: ImageView
        var fruitName: TextView

        init {
            fruitImage = view.findViewById<View>(R.id.fruit_image) as ImageView
            fruitName = view.findViewById<View>(R.id.fruitname) as TextView
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item_for_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val (name, imageId) = mFruitList[position]
        holder.fruitImage.setImageResource(imageId)
        holder.fruitName.text = name
    }

    override fun getItemCount(): Int {
        return mFruitList.size
    }
}
