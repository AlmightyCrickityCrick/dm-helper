package com.example.dmhelper.presentation.home

import androidx.lifecycle.ViewModel
import com.example.dmhelper.data.user.UserRepository
import com.example.dmhelper.data.user.UserRepositoryImpl

class HomeViewModel(private val repository: UserRepository = UserRepositoryImpl()) : ViewModel(){

    fun getUsername() :String{
        return "Mollymauk"
    }

}