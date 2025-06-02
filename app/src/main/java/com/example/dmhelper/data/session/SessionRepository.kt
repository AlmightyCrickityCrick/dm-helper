package com.example.dmhelper.data.session

import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.common.Util

class SessionRepository {
    private val sessionList = Util.mockSessions

    suspend fun getSessions(userId: Int, campaignId: Int): SessionListDTO {
        return sessionList
    }
    suspend fun createSession(session: CreateSessionDTO): Result<CreateSessionResponseDTO> {
        val id = sessionList.list.size
        sessionList.list.add(SessionDTO(id, session.name, true))
        return Result.Success(CreateSessionResponseDTO(id))
    }
}