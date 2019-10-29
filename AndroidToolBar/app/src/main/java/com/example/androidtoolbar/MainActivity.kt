package com.example.androidtoolbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        btn_set_toolbar.setOnClickListener {
            Intent(MainActivity@this,SetToolBarActivity::class.java).apply {
                startActivity(this)
            }
        }

        btn_search_action.setOnClickListener {
            Intent(MainActivity@this,SearchToolBarActivity::class.java).apply {
                startActivity(this)
            }
        }

        btn_share_action.setOnClickListener {
            Intent(MainActivity@this,ShareToolBarActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}
