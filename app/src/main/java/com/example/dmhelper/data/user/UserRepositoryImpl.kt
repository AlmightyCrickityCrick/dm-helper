package com.example.dmhelper.data.user

import com.example.dmhelper.data.common.Result

class UserRepositoryImpl:UserRepository {
    override suspend fun login(authDTO: AuthDTO): Result<LoginResponseDTO> {
        return Result.Success(LoginResponseDTO("username", 3))
    }

    override suspend fun register(registerDTO: RegisterDTO): Result<LoginResponseDTO> {
        return Result.Success(LoginResponseDTO("username", 3))
    }

    override suspend fun signout(): Result<Boolean> {
        TODO("Not yet implemented")
    }
}