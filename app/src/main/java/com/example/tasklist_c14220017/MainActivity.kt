package com.example.tasklist_c14220017

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val _nama = mutableListOf<String>()
    private val _tanggal = mutableListOf<String>()
    private val _deskripsi = mutableListOf<String>()

    private var arListTask = arrayListOf<listTask>()
    private lateinit var _rvListTask: RecyclerView

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

        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            val intent = Intent(this@MainActivity, addTask::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_TASK)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == Activity.RESULT_OK) {
            val nama = data?.getStringExtra("nama") ?: return
            val tanggal = data.getStringExtra("tanggal") ?: return
            val deskripsi = data.getStringExtra("deskripsi") ?: return
            addTask(nama, tanggal, deskripsi)
        }
    }

    fun addTask(nama: String, tanggal: String, deskripsi: String) {
        _nama.add(nama)
        _tanggal.add(tanggal)
        _deskripsi.add(deskripsi)
        arListTask.add(listTask(nama, tanggal, deskripsi))
        // Update RecyclerView adapter here
    }

    companion object {
        const val REQUEST_CODE_ADD_TASK = 1
    }

}