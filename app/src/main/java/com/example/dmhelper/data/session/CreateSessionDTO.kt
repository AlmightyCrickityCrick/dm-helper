package com.example.dmhelper.data.session

data class CreateSessionDTO(val name: String, val campaignId: Int, val map: String, val userIdAllowed : ArrayList<Int>, )

data class CreateSessionResponseDTO(val id: Int)