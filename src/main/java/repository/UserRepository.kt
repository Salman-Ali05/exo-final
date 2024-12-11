package com.example.kotlin_tp_3.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlin_tp_3.R
import dao.UserDao
import models.User
import org.json.JSONObject

class UserRepository(private val userDao: UserDao) {

    // LiveData to observe all users from the database
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    // Function to insert a user into the database
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    // Function to delete a user from the database
    suspend fun delete(user: User) {
        userDao.deleteUser(user)
    }

    // Function to parse JSON data from the raw resources and return a list of users
    fun parseJson(context: Context): List<User> {
        val users = mutableListOf<User>()
        val jsonString = context.resources.openRawResource(R.raw.users).bufferedReader().use { it.readText() }
        val jsonArray = JSONObject(jsonString).getJSONArray("users")

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            users.add(
                User(
                    name = jsonObject.getString("name"),
                    email = jsonObject.getString("email")
                )
            )
        }
        return users
    }

    suspend fun populateDatabase(context: Context) {
        val users = parseJson(context).filter { it.name.isNotEmpty() && it.email.isNotEmpty() }
        users.forEach { userDao.insert(it) }
    }

}
