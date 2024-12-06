package com.example.bodymassindex

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var resultTV: TextView
    private lateinit var imageIV: ImageView
    private lateinit var infoTV: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        resultTV = findViewById(R.id.resultTV)
        imageIV = findViewById(R.id.imageIV)
        infoTV = findViewById(R.id.infoTV)

        val height = intent.getStringExtra("height" )?.toDoubleOrNull()
        val weight = intent.getStringExtra("weight" )?.toDoubleOrNull()
        var result = 0.0

        if (height != null && weight != null) {
            result = index(height, weight)
            resultTV.text = result.toString()
        } else resultTV.text = "Введите корректные значения массы и роста"
        val recomendations = Recomendations()
        when {
            result < 18.5 -> {
                imageIV.setImageResource(R.drawable.thin)
                infoTV.text = recomendations.thin
            }
            result > 18.5 && result <= 25 -> {
                imageIV.setImageResource(R.drawable.normal)
                infoTV.text = recomendations.normal
            }
            result > 25 -> {
                imageIV.setImageResource(R.drawable.fat)
                infoTV.text = recomendations.fat
            }
        }
    }

    private fun index(height: Double, weight: Double): Double {
        return weight / ((height/100)*(height/100))
    }
}