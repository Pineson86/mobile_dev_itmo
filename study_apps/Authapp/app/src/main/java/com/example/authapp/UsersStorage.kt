package com.example.authapp

object UsersStorage {
    private val users = mutableMapOf<String, String>()

    fun addUser(login: String, pass: String) {
        users[login] = pass
    }

    fun validate(login: String, pass: String): Boolean {
        return users[login] == pass
    }

    fun getPassword(login: String): String? {
        return users[login]
    }
}
