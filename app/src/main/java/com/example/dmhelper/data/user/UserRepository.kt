package com.example.dmhelper.data.user

interface UserRepository {
    suspend fun login(authDTO: AuthDTO): Result<LoginResponseDTO> //Temporarily
    suspend fun register(registerDTO: RegisterDTO): Result<LoginResponseDTO>
    suspend fun signout(): Result<Boolean>
}