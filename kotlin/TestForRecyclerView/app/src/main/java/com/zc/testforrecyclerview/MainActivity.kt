package com.zc.testforrecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mlList:List<String> = listOf("a","b","c","d","e","f","g","h","y","z")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var manager = LinearLayoutManager(this)
        recycler_view.layoutManager = manager
        val adapter = MyRecyclerViewAdapter(this, mlList)
        recycler_view.adapter = adapter

    }
}
