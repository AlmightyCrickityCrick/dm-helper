package com.example.dmhelper.data.character

import com.example.dmhelper.data.common.Util

class CharacterRepository {
    suspend fun getAllCharacters(id: Int): CharacterListDTO {
        return Util.mockCharacters
    }
}