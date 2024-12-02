package com.example.tasklist_c14220017

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val taskList: List<listTask>, private val activity: MainActivity) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaTextView: TextView = itemView.findViewById(R.id.tv_nama)
        val tanggalTextView: TextView = itemView.findViewById(R.id.tv_tanggal)
        val deskripsiTextView: TextView = itemView.findViewById(R.id.tv_deskripsi)
        val btnStart: Button = itemView.findViewById(R.id.btn_start)
        val btnEdit: Button = itemView.findViewById(R.id.btn_edit)
        val btnHapus: Button = itemView.findViewById(R.id.btn_hapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.namaTextView.text = currentItem.nama
        holder.tanggalTextView.text = currentItem.tanggal
        holder.deskripsiTextView.text = currentItem.deskripsi


        holder.btnStart.setOnClickListener {
            if (holder.btnStart.text == "Start") {
                holder.btnStart.text = "Selesai"
            }
            else if (holder.btnStart.text == "Selesai") {
                holder.btnStart.text = "Start"
            }
        }
        holder.btnEdit.setOnClickListener {
            val intent = Intent(activity, addTask::class.java)
            intent.putExtra("position", position)
            intent.putExtra("nama", currentItem.nama)
            intent.putExtra("tanggal", currentItem.tanggal)
            intent.putExtra("deskripsi", currentItem.deskripsi)
            activity.startActivityForResult(intent, MainActivity.REQUEST_CODE_EDIT_TASK)
        }
        holder.btnHapus.setOnClickListener {
            activity.deleteTask(position)
        }

    }
}