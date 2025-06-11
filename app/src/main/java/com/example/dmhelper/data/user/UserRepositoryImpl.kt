package com.example.dmhelper.data.user

import android.content.Context
import android.util.Log
import com.example.dmhelper.data.common.ApiService
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.user.dto.AuthDTO
import com.example.dmhelper.data.user.dto.LoginResponseDTO
import com.example.dmhelper.data.user.dto.RegisterDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.io.File
import java.util.ArrayList

class UserRepositoryImpl(private val api: ApiService, private val context: Context) : UserRepository {
    var users = arrayListOf(
        RegisterDTO("crick", "crick@gmail.com", "crick123"),
        RegisterDTO("lina", "lina@gmail.com", "crick123"),
    )
    val gson = Gson()
    var currentUserId: Int = -1
    lateinit var currentUser: LoginResponseDTO

    val fileName = "auth.json"

    init {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            saveUsersToFile()
        } else {
            users = readUsersFromFile()
        }
    }

    override suspend fun login(authDTO: AuthDTO): Result<LoginResponseDTO> {
        val response = try {
            api.login(authDTO)
        } catch (e: HttpException) {
            when (e.code()) {
                401, 404 -> {
                    Log.e("API", "Unauthorized: ${e.message()}")
                    return handleOffline(authDTO)
                }
                else -> {
                    Log.e("API", "Something went wrong: ${e.message()}")
                    return handleOffline(authDTO)
                }
            }
        } catch (error: Error) {
            Log.d("UserRepository", "Couldn't login. Switch to offline mode")
            return handleOffline(authDTO)
        }
        currentUser = response as LoginResponseDTO
        currentUserId = currentUser.id
        return Result.Success(currentUser)
    }

    private fun handleOffline(authDTO: AuthDTO): Result<LoginResponseDTO> {
        val user = users.filter { it.email == authDTO.email && it.password == authDTO.password }
        if (user.isNotEmpty()) {
            currentUserId = users.indexOf(user[0])
            currentUser = LoginResponseDTO(user[0].username, currentUserId)
            return Result.Success(currentUser)
        }
        return Result.ServerError
    }

    override suspend fun register(registerDTO: RegisterDTO): Result<LoginResponseDTO> {
        val response = try {
            api.register(registerDTO)
        } catch (e: HttpException) {
            when (e.code()) {
                401, 404 -> {
                    Log.d("Repository", "Irina broke something")
                    LoginResponseDTO(registerDTO.username, users.size)
                }
                else -> {
                    Log.d("Repository", "We will never recover from this")
                    LoginResponseDTO(registerDTO.username, users.size)
                }
            }
        }
        catch (error: Error) {
            Log.d("UserRepository", "Something went wrong")
            LoginResponseDTO(registerDTO.username, users.size)
        }
        currentUser = response
        currentUserId = response.id
        users.add(registerDTO)
        saveUsersToFile()
        return Result.Success(LoginResponseDTO(registerDTO.username, currentUserId))
    }

    private fun saveUsersToFile() {
        val jsonString = gson.toJson(users)
        File(context.filesDir, fileName).writeText(jsonString)
    }

    private fun readUsersFromFile(): ArrayList<RegisterDTO> {
        val file = File(context.filesDir, fileName)
        val json = file.readText()
        val type = object : TypeToken<ArrayList<RegisterDTO>>() {}.type
        return gson.fromJson(json, type)
    }

    override suspend fun signout(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getId(): Int {
        return currentUserId
    }

    override suspend fun getUsername(): String {
        return currentUser.username
    }
}