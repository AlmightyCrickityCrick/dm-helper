package com.example.dmhelper.data.session

data class SessionListDTO(val list: ArrayList<SessionDTO>)

data class SessionDTO(val id: Int, val name: String, val isAccessible: Boolean)
