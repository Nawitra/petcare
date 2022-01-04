package com.seventhgroup.petcare.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seventhgroup.petcare.R

class DateAdapter(private val dateList : ArrayList<String>) : RecyclerView.Adapter<DateAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.history_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = dateList[position]

        holder.tvHistDate.text = currentitem

    }

    override fun getItemCount(): Int {
        return dateList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val tvHistDate : TextView = itemView.findViewById(R.id.tv_hist_date)
    }

}