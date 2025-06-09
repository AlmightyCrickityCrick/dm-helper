package com.example.dmhelper.data.session

import android.net.Uri
import com.example.dmhelper.data.common.ApiService
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.common.Util
import java.io.File

class SessionRepository(private val api: ApiService) {
    private val sessionList = Util.mockSessions

    suspend fun getSessions(userId: Int, campaignId: Int): SessionListDTO {
        return sessionList
    }
    suspend fun createSession(session: CreateSessionDTO): Result<CreateSessionResponseDTO> {
        val id = sessionList.list.size
        sessionList.list.add(SessionDTO(id, session.name, true))
        return Result.Success(CreateSessionResponseDTO(id))
    }

    suspend fun uploadMap(uri: Uri, type: String?, file: File): String {
        return uri.lastPathSegment.toString()
    }
}