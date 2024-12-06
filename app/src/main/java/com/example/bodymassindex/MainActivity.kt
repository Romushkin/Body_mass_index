package com.example.bodymassindex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var heightET: EditText
    private lateinit var weightET: EditText

    private lateinit var calcBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        heightET = findViewById(R.id.heightET)
        weightET = findViewById(R.id.weightET)
        calcBTN = findViewById(R.id.calcBTN)

        calcBTN.setOnClickListener {
            if (heightET.text.isEmpty() || weightET.text.isEmpty()) return@setOnClickListener
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("height", heightET.text.toString())
            intent.putExtra("weight", weightET.text.toString())
            startActivity(intent)
        }

    }
}