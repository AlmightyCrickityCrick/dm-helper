package com.example.dmhelper.data.session

data class CreateSessionDTO(val name: String, val campaignId: Int, val map: String, val charIdAllowed : ArrayList<Int>, val id: Int?=null)

data class CreateSessionResponseDTO(val id: Int)