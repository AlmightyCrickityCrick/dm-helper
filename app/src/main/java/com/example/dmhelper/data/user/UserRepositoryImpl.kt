package com.example.dmhelper.data.user

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