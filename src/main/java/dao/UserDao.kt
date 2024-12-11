package dao

import androidx.lifecycle.LiveData
import androidx.room.*
import models.User

@Dao
interface UserDao {

    // Insert a new user
    @Insert
    suspend fun insert(user: User)

    // Retrieve all users ordered by name
    @Query("SELECT * FROM user_table ORDER BY name ASC")
    fun getAllUsers(): LiveData<List<User>>

    // Delete a user
    @Delete
    suspend fun deleteUser(user: User)

    // Update an existing user
    @Update
    suspend fun updateUser(user: User)
}