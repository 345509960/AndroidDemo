package com.example.androidtoolbar

import android.os.Bundle

class SetToolBarActivity : ToolBarActivity() {

    override fun getContenLayout(): Int {
       return R.layout.activity_set_tool_bar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarTitle("设置应用栏")
        setToolBarBackgroundColor(android.R.color.holo_red_dark)
        setToolBarColor(android.R.color.white)
    }

}
