package com.example.dmhelper.data.character

data class CharacterListDTO (val character: ArrayList<CharacterShortDTO>)

data class CharacterShortDTO(val id: Int, val name: String, val raceId: Int, val classId: Int )