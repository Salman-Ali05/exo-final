package com.example.kotlin_tp_3.utils

import android.content.Context
import android.util.Log
import com.example.kotlin_tp_3.R
import models.User
import org.json.JSONObject

fun parseJson(context: Context): List<User> {
    val users = mutableListOf<User>()
    val jsonString = context.resources.openRawResource(R.raw.users).bufferedReader().use { it.readText() }
    val jsonArray = JSONObject(jsonString).getJSONArray("users")

    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)

        // Validate JSON fields
        val name = jsonObject.optString("name", "")
        val email = jsonObject.optString("email", "")

        if (name.isNotEmpty() && email.isNotEmpty()) {
            users.add(User(name = name, email = email))
        } else {
            // Log or handle missing/invalid fields
            Log.e("JsonParser", "Invalid user data: $jsonObject")
        }
    }
    return users
}