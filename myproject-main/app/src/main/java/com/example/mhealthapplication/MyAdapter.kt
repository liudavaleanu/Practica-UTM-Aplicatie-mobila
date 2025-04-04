package com.example.mhealthapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val examList: List<Item>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val examItem = examList[position]

            holder.examName.text = examItem.examName
        }

        override fun getItemCount(): Int {
            return examList.size
        }

        // ViewHolder class
        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val examName: TextView = itemView.findViewById(R.id.examName)
        }

}