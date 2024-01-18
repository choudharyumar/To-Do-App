package com.umar.taskmate.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umar.taskmate.R
import com.umar.taskmate.data.Notes

class MyAdapter(private val myAdapterInterface: MyAdapterInterface) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var toDoList: List<Notes> = listOf()
    fun setData(toDoList: List<Notes>) {
        this.toDoList = toDoList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 2) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.left_layout, parent, false)
            LeftViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.right_layout, parent, false)
            RightViewHolder(itemView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (toDoList[position].isLeftMessage) 2 else 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LeftViewHolder) {
            holder.tvHeadingLeft.text = toDoList[position].messages
            holder.itemView.setOnClickListener {
                myAdapterInterface.onTodoClick(position)
            }
        } else if (holder is RightViewHolder) {
            holder.tvHeadingRight.text = toDoList[position].messages
            holder.itemView.setOnClickListener {
                myAdapterInterface.onTodoClick(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHeadingLeft: TextView = itemView.findViewById(R.id.tvHeadingLeft)
    }

    class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHeadingRight: TextView = itemView.findViewById(R.id.tvHeadingRight)
    }

}



