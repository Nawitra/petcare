package com.seventhgroup.petcare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seventhgroup.petcare.R

class HistoryAdapter (private val listHistory: ArrayList<String>) : RecyclerView.Adapter<HistoryAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHistDate : TextView = itemView.findViewById(R.id.tv_hist_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val history = listHistory[position]
        holder.tvHistDate.text = history
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }

}