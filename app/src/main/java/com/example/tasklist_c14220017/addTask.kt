package com.example.tasklist_c14220017

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class addTask : AppCompatActivity() {

    private var position: Int = -1
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

        val intent = intent
        if(intent.hasExtra("nama")){
            _edt_nama.setText(intent.getStringExtra("nama"))
            _edt_tanggal.setText(intent.getStringExtra("tanggal"))
            _edt_deskripsi.setText(intent.getStringExtra("deskripsi"))
            position = intent.getIntExtra("position", -1)
        }

        _edt_tanggal.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                _edt_tanggal.setText(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        _btn_simpan.setOnClickListener {
            val nama = _edt_nama.text.toString()
            val tanggal = _edt_tanggal.text.toString()
            val deskripsi = _edt_deskripsi.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("nama", nama)
            resultIntent.putExtra("tanggal", tanggal)
            resultIntent.putExtra("deskripsi", deskripsi)
            resultIntent.putExtra("position", position)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}