package com.example.recyclerviewdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_list.setOnClickListener {
            Intent(MainActivity@this,SimpleActivity::class.java).apply {
                startActivity(this)
            }
        }

        btn_grid.setOnClickListener {
            Intent(MainActivity@this,SimpleGridActivity::class.java).apply {
                startActivity(this)
            }
        }

        btn_grid_scroll.setOnClickListener {
            Intent(MainActivity@this,SimpleGridScrollActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}
