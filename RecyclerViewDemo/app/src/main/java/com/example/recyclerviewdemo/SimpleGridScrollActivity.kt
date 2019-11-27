package com.example.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.adpater.FruitAdapter
import com.example.recyclerviewdemo.adpater.FruitGridAdapter
import com.example.recyclerviewdemo.model.Fruit


class SimpleGridScrollActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        initFruits()
        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        val adapter = FruitGridAdapter(fruitList)
        recyclerView.adapter = adapter
    }

    private fun initFruits() {
        for (i in 0..1) {
            val apple = Fruit("Apple", R.mipmap.ic_launcher)
            fruitList.add(apple)
            val banana = Fruit("Banana", R.mipmap.ic_launcher)
            fruitList.add(banana)
            val orange = Fruit("Orange", R.mipmap.ic_launcher)
            fruitList.add(orange)
            val watermelon =
                Fruit("Watermelon", R.mipmap.ic_launcher)
            fruitList.add(watermelon)
            val pear = Fruit("Pear", R.mipmap.ic_launcher)
            fruitList.add(pear)
            val grape = Fruit("Grape", R.mipmap.ic_launcher)
            fruitList.add(grape)
            val pineapple =
                Fruit("Pineapple", R.mipmap.ic_launcher)
            fruitList.add(pineapple)
            val strawberry =
                Fruit("Strawberry", R.mipmap.ic_launcher)
            fruitList.add(strawberry)
            val cherry = Fruit("Cherry", R.mipmap.ic_launcher)
            fruitList.add(cherry)
            val mango = Fruit("Mango", R.mipmap.ic_launcher)
            fruitList.add(mango)

        }
    }
}
