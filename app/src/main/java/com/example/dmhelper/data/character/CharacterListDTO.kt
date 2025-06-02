package com.example.dmhelper.data.character

data class CharacterListDTO(val character: ArrayList<CharacterShortDTO>)

data class CharacterShortDTO(
    val id: Int,
    val name: String,
    val raceId: Int,
    val classId: Int,
    val userId: Int? = null
)

fun CharacterListDTO.toOptions(): MutableMap<Int, String> {
    val tempMap = mutableMapOf<Int, String>()
    for (c in this.character){
        tempMap[c.id] = c.name
    }
    return tempMap
}