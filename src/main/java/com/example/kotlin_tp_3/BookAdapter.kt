package com.example.kotlin_tp_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import models.User

class BookAdapter(private var userList: List<User>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    // ViewHolder class to bind views
    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tvBookTitle)
        val authorTextView: TextView = itemView.findViewById(R.id.tvBookAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        // Inflate the item layout and create a ViewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        // Bind data to the ViewHolder
        val user = userList[position]
        holder.titleTextView.text = user.name
        holder.authorTextView.text = user.email
    }

    override fun getItemCount(): Int = userList.size

    // Method to update the user list and refresh the RecyclerView
    fun setUsers(users: List<User>?) {
        userList = users ?: emptyList() // Use empty list if null
        notifyDataSetChanged()
    }
}
