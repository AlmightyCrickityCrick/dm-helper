package com.example.dmhelper.data.user

import com.example.dmhelper.data.common.ApiService
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.user.dto.AuthDTO
import com.example.dmhelper.data.user.dto.LoginResponseDTO
import com.example.dmhelper.data.user.dto.RegisterDTO

class UserRepositoryImpl(private val api: ApiService):UserRepository {
    override suspend fun login(authDTO: AuthDTO): Result<LoginResponseDTO> {
        return Result.Success(LoginResponseDTO("username", 3))
    }

    override suspend fun register(registerDTO: RegisterDTO): Result<LoginResponseDTO> {
        return Result.Success(LoginResponseDTO("username", 3))
    }

    override suspend fun signout(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getId(): Int {
        return 1
    }
}