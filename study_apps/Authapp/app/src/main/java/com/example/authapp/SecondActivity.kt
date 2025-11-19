package com.example.authapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val userLogin = intent.getStringExtra("login") ?: ""

        val textWelcome = findViewById<TextView>(R.id.textWelcome)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        textWelcome.text = "Привет, $userLogin!"

        btnLogout.setOnClickListener {
            finish()   // вернуться на MainActivity
        }
    }
}
