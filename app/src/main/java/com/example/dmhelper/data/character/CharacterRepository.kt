package com.example.dmhelper.data.character

import com.example.dmhelper.data.common.Util

class CharacterRepository {
    suspend fun getAllUserCharacters(id: Int): CharacterListDTO {
        val char  = Util.mockCharacters.character.filter { it.userId == id } as ArrayList<CharacterShortDTO>
        return CharacterListDTO(char)
    }

    suspend fun getAllCampaignCharacters(id: Int): CharacterListDTO {
        return Util.mockCharacters
    }
}