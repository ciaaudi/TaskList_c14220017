package com.example.tasklist_c14220017

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterRecView (private val list: ArrayList<listTask>) : RecyclerView
.Adapter<adapterRecView.ListViewHolder>() {


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _tv_nama = itemView.findViewById<TextView>(R.id.tv_nama)
        var _tv_tanggal = itemView.findViewById<TextView>(R.id.tv_tanggal)
        var _tv_deskripsi = itemView.findViewById<TextView>(R.id.tv_deskripsi)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var listTask = list[position]

        holder._tv_nama.setText(listTask.nama)
        holder._tv_tanggal.setText(listTask.tanggal)
        holder._tv_deskripsi.setText(listTask.deskripsi)
    }

}