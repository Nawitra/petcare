package com.seventhgroup.petcare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seventhgroup.petcare.R

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ListViewHolder>() {
    private var mData = ArrayList<String>()

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHistDate : TextView = itemView.findViewById(R.id.tv_hist_date)
    }

    fun setData(items: ArrayList<String>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val history = mData[position]
        holder.tvHistDate.text = history
    }

    override fun getItemCount(): Int {
        return mData.size
    }

}