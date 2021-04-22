package com.example.bellaonojie.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.bellaonojie.BellaApplication
import com.example.bellaonojie.dao.UserDao
import com.example.bellaonojie.models.User
import com.example.bellaonojie.repository.UsersRepository
import kotlinx.coroutines.launch

class UserViewModel(val application: BellaApplication) : ViewModel() {

    private val repository = application.usersRepository

    private val _user = MutableLiveData<User>()
    val user: LiveData<User?>
        get() = _user

    init {
        makeRequest()
    }

    private fun makeRequest() {
        viewModelScope.launch {
            _user.value = repository.getUserDetails(101)
            Log.d("User", "User: ${user.value?.username} ")
        }
    }

    fun checkUserData(email: String, password: String): LiveData<User> {
        return repository.getUserDataToLogin(email, password)
    }

    suspend fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insert(user)
        }
    }
}

class UserViewModelFactory(
        private val application: BellaApplication) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}