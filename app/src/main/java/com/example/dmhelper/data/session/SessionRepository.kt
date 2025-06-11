package com.example.dmhelper.data.session

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.dmhelper.data.common.ApiService
import com.example.dmhelper.data.common.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import kotlin.random.Random

class SessionRepository(private val api: ApiService, private val context: Context) {
    private var sessionList = arrayListOf<CreateSessionDTO>()

    val gson = Gson()
    var campaignId: Int = -1

    val campaignFile: String
        get() = "campaign_${campaignId}_sessions.json"

    suspend fun fetchData() {
        val file1 = File(context.filesDir, campaignFile)
        if (file1.exists()){
            sessionList = readSessionsFromFile(campaignFile)
        } else {
            sessionList = arrayListOf()
            saveSessionsToFile()
        }
    }

    suspend fun getSessions(characterId: Int? = null, campaignId: Int, isOwner: Boolean): SessionListDTO {
        this.campaignId = campaignId
        Log.d("Repository", "Fetching Sessions for $campaignFile")
        fetchData()
        val sessionShort = sessionList.map { session -> SessionShortDTO(session.id?:0, session.name, session.map, characterId in session.charIdAllowed || isOwner) }
        return SessionListDTO(sessionShort as ArrayList<SessionShortDTO>)
    }
    suspend fun createSession(session: CreateSessionDTO): Result<CreateSessionResponseDTO> {
        val id = generateId()
        sessionList.add(session.copy(id = id))
        saveSessionsToFile()
        return Result.Success(CreateSessionResponseDTO(id))
    }

    suspend fun uploadMap(uri: Uri, type: String?, file: File): String {
        return uri.lastPathSegment.toString()
    }

    suspend fun generateId(): Int {
        return Random.nextInt(100000, 999999)
    }

    private fun saveSessionsToFile() {
        val sessionJson= gson.toJson(sessionList)
        File(context.filesDir, campaignFile).writeText(sessionJson)
    }

    private fun readSessionsFromFile(name: String): ArrayList<CreateSessionDTO> {
        val file = File(context.filesDir, name)
        if (!file.exists() || file.readText().isBlank()) {
            Log.d("Repository", "$name is blank")
            return arrayListOf() // Return empty list if file is missing or empty
        }
        val json = file.readText()
        val type = object : TypeToken<ArrayList<CreateSessionDTO>>() {}.type
        return gson.fromJson(json, type)
    }
}