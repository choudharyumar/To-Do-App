package com.umar.taskmate.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umar.taskmate.R
import com.umar.taskmate.data.Notes

class MyAdapter(private val myAdapterInterface: MyAdapterInterface) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var toDoList: List<Notes> = listOf()
    fun setData(toDoList: List<Notes>) {
        this.toDoList = toDoList
        notifyDataSetChanged()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvHeading.text = toDoList[position].messages
        holder.itemView.setOnClickListener {
            myAdapterInterface.onTodoClick(position)
        }
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)

    }

}



