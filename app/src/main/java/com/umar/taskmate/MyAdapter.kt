package com.umar.taskmate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val ToDoList: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mlistener: onItemClickListener

    interface onItemClickListener {
        fun OnItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mlistener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout, parent, false)
        return MyViewHolder(itemView, mlistener)

    }

    override fun getItemCount(): Int {
        return ToDoList.size
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
      val currentItem=ToDoList[position]
        holder.tvHeading.text=currentItem
        holder.itemView.setOnClickListener{
        ToDoList.removeAt(position)
            notifyItemChanged(position)
            notifyItemRangeChanged(position,ToDoList.size)
        }

    }

    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)

        init {
            itemView.setOnClickListener {
                listener.OnItemClick(adapterPosition)
            }
        }
    }

}