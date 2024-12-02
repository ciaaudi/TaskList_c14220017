package com.example.tasklist_c14220017

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val _nama = mutableListOf<String>()
    private val _tanggal = mutableListOf<String>()
    private val _deskripsi = mutableListOf<String>()

    private var arListTask = arrayListOf<listTask>()
    private lateinit var _rvListTask: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    internal lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _rvListTask = findViewById(R.id.rvList)
        taskAdapter = TaskAdapter(arListTask, this)
        _rvListTask.layoutManager = LinearLayoutManager(this)
        _rvListTask.adapter = taskAdapter

        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            val intent = Intent(this@MainActivity, addTask::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_TASK)
        }

        sharedPreferences = getSharedPreferences("task", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        loadTasksFromPreferences()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val nama = data?.getStringExtra("nama") ?: return
            val tanggal = data.getStringExtra("tanggal") ?: return
            val deskripsi = data.getStringExtra("deskripsi") ?: return
            val position = data.getIntExtra("position", -1)

            if (requestCode == REQUEST_CODE_ADD_TASK) {
                addTask(nama, tanggal, deskripsi)
            } else if (requestCode == REQUEST_CODE_EDIT_TASK && position != -1) {
                updateTask(position, nama, tanggal, deskripsi)
            }
        }
    }

    fun addTask(nama: String, tanggal: String, deskripsi: String) {
        val task = listTask(nama, tanggal, deskripsi)
        arListTask.add(task)
        taskAdapter.notifyDataSetChanged()
    }

    fun updateTask(position: Int, nama: String, tanggal: String, deskripsi: String) {
        val task = arListTask[position]
        removeTaskFromPreferences(task)
        task.nama = nama
        task.tanggal = tanggal
        task.deskripsi = deskripsi
        saveTaskToPreferences(task)
        taskAdapter.notifyDataSetChanged()
    }

    fun deleteTask(position: Int) {
        if (position >= 0 && position < arListTask.size) {
            val task = arListTask[position]
            arListTask.removeAt(position)
            taskAdapter.notifyItemRemoved(position)
            removeTaskFromPreferences(task)
        }
    }

    fun saveTaskToPreferences(task: listTask) {
        val taskSet = sharedPreferences.getStringSet("task", mutableSetOf())?.toMutableSet()
        taskSet?.add("${task.nama},${task.tanggal},${task.deskripsi}")
        editor.putStringSet("task", taskSet)
        editor.apply()
    }

    fun removeTaskFromPreferences(task: listTask) {
        val taskSet = sharedPreferences.getStringSet("task", mutableSetOf())?.toMutableSet()
        taskSet?.remove("${task.nama},${task.tanggal},${task.deskripsi}")
        editor.putStringSet("task", taskSet)
        editor.apply()
    }

    private fun loadTasksFromPreferences() {
        val taskSet = sharedPreferences.getStringSet("task", mutableSetOf())
        taskSet?.forEach { taskString ->
            val taskData = taskString.split(",")
            val task = listTask(taskData[0], taskData[1], taskData[2])
            arListTask.add(task)
        }
        taskAdapter.notifyDataSetChanged()
    }

    companion object {
        const val REQUEST_CODE_ADD_TASK = 1
        const val REQUEST_CODE_EDIT_TASK = 2
    }
}