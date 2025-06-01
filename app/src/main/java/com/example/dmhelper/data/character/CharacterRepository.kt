package com.example.dmhelper.data.character

class CharacterRepository {
    suspend fun getAllCharacters(id: Int): CharacterListDTO {
        return Util.mockCharacters
    }
}