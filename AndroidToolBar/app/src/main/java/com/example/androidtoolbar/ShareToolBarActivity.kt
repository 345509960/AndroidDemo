package com.example.androidtoolbar

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import android.content.Intent





class ShareToolBarActivity : ToolBarActivity() {
    override fun getContenLayout(): Int {
        return R.layout.activity_search_tool_bar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        val shareItem = menu?.findItem(R.id.action_share)
        val myShareActionProvider =
            MenuItemCompat.getActionProvider(shareItem) as ShareActionProvider
        val myShareIntent = Intent(Intent.ACTION_SEND)
        myShareIntent.type = "image/*"
        myShareIntent.putExtra(Intent.EXTRA_STREAM, "sadsad")
        myShareActionProvider.setShareIntent(myShareIntent)
        return super.onCreateOptionsMenu(menu)
    }
}
