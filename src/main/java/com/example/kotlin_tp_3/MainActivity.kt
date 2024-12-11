package com.example.kotlin_tp_3

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_tp_3.repository.UserRepository
import com.example.kotlin_tp_3.ui.settings.SettingsFragment
import database.UserDatabase
import viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = UserDatabase.getDatabase(applicationContext)
        val userDao = database.userDao()
        val repository = UserRepository(userDao)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = BookAdapter(emptyList()) // Provide an empty list initially
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observe LiveData and update the RecyclerView
        userViewModel.allUsers.observe(this, Observer { users ->
            adapter.setUsers(users)
        })

        // Navigate to SettingsFragment
        val btnSettings: Button = findViewById(R.id.btnSettings)
        btnSettings.setOnClickListener {
            // Hide the button when navigating to the fragment
            btnSettings.visibility = Button.GONE
            recyclerView.visibility = RecyclerView.GONE

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SettingsFragment())
                .addToBackStack(null) // Optional: Enables back navigation
                .commit()
        }

        // Handle back navigation to restore button visibility
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                // Show the button and RecyclerView when back to MainActivity
                btnSettings.visibility = Button.VISIBLE
                recyclerView.visibility = RecyclerView.VISIBLE
            }
        }
    }
}
