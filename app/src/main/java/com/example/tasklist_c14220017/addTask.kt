package com.example.tasklist_c14220017

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class addTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val _edt_nama = findViewById<EditText>(R.id.edt_nama)
        val _edt_tanggal = findViewById<EditText>(R.id.edt_tanggal)
        val _edt_deskripsi = findViewById<EditText>(R.id.edt_deskripsi)

        val _btn_simpan = findViewById<Button>(R.id.btn_simpan)

        _btn_simpan.setOnClickListener {
            val nama = _edt_nama.text.toString()
            val tanggal = _edt_tanggal.text.toString()
            val deskripsi = _edt_deskripsi.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("nama", nama)
            resultIntent.putExtra("tanggal", tanggal)
            resultIntent.putExtra("deskripsi", deskripsi)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}