package com.example.dmhelper.data.user

import com.example.dmhelper.data.common.Result

interface UserRepository {
    suspend fun login(authDTO: AuthDTO): Result<LoginResponseDTO> //Temporarily
    suspend fun register(registerDTO: RegisterDTO): Result<LoginResponseDTO>
    suspend fun signout(): Result<Boolean>
}