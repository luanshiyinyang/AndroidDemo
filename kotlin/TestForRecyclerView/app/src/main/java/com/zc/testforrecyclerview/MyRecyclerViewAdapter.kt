package com.zc.testforrecyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class MyRecyclerViewAdapter(): RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    private var mData : List<String>? = null
    private var context : Context? = null

    constructor(context:Context, list:List<String>) : this() {
        this.context = context
        this.mData = list
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var content:TextView? = null
        var myView:View? = null

        constructor(view:View) : super(view) {
            myView = view
            content = view.findViewById(R.id.content) as TextView
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var str = mData?.get(position)
        holder.content?.setText(str)
    }
    override fun getItemCount():Int{
        return mData!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content, null, false)
        val holder = ViewHolder(view)
        holder.myView?.setOnClickListener(View.OnClickListener {
            val position = holder.adapterPosition
            Toast.makeText(context, "点击了第"+position.toString()+"个", Toast.LENGTH_LONG).show()
        })
        return holder
    }





}
