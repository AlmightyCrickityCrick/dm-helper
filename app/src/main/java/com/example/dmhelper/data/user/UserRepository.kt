package com.example.dmhelper.data.user

import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.user.dto.AuthDTO
import com.example.dmhelper.data.user.dto.LoginResponseDTO
import com.example.dmhelper.data.user.dto.RegisterDTO

interface UserRepository {
    suspend fun login(authDTO: AuthDTO): Result<LoginResponseDTO> //Temporarily
    suspend fun register(registerDTO: RegisterDTO): Result<LoginResponseDTO>
    suspend fun signout(): Result<Boolean>
    suspend fun getId() : Int
}