package com.example.authapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginField = findViewById<EditText>(R.id.editLogin)
        val passField = findViewById<EditText>(R.id.editPassword)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnForgot = findViewById<Button>(R.id.btnForgot)

        // Регистрация
        btnRegister.setOnClickListener {
            val login = loginField.text.toString()
            val pass = passField.text.toString()

            if (login.isEmpty() || pass.isEmpty()) {
                showAlert("Ошибка", "Заполните логин и пароль")
                return@setOnClickListener
            }

            UsersStorage.addUser(login, pass)

            goToSecond(login)
        }

        // Вход
        btnLogin.setOnClickListener {
            val login = loginField.text.toString()
            val pass = passField.text.toString()

            if (UsersStorage.validate(login, pass)) {
                goToSecond(login)
            } else {
                showAlert("Ошибка", "Имя или пароль неверны")
            }
        }

        // Забыли пароль
        btnForgot.setOnClickListener {
            val login = loginField.text.toString()
            if (login.isEmpty()) {
                showAlert("Информация", "Введите логин")
                return@setOnClickListener
            }

            val pass = UsersStorage.getPassword(login)

            if (pass == null) {
                showAlert("Данные", "Пользователь не найден")
            } else {
                showAlert("Ваш пароль", "Логин: $login\nПароль: $pass")
            }
        }
    }

    private fun goToSecond(login: String) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("login", login)
        startActivity(intent)
    }

    private fun showAlert(title: String, text: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(text)
            .setPositiveButton("OK", null)
            .show()
    }
}
