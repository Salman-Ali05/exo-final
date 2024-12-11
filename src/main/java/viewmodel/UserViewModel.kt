package viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import database.UserDatabase
import models.User

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = UserDatabase.getDatabase(application).userDao()
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    fun insert(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(user)
        }
    }

    fun delete(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.deleteUser(user)
        }
    }
}
