package com.example.androidtoolbar

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView

class SearchToolBarActivity : ToolBarActivity() {
    override fun getContenLayout(): Int {
        return R.layout.activity_search_tool_bar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_serach, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView




        // Configure the search info and add any event listeners...

        return super.onCreateOptionsMenu(menu)
    }


}
