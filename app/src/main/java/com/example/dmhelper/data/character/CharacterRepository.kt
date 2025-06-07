package com.example.dmhelper.data.character

import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.common.Util

class CharacterRepository {
    var characters : ArrayList<CharacterShortDTO>

    init {
        characters = Util.mockCharacters.character
    }

    suspend fun getAllUserCharacters(id: Int): CharacterListDTO {
        if (characters.isEmpty()) characters = Util.mockCharacters.character.filter { it.userId == id } as ArrayList<CharacterShortDTO>
        return CharacterListDTO(characters)
    }

    suspend fun getAllCampaignCharacters(id: Int): CharacterListDTO {
        return Util.mockCharacters
    }

    suspend fun createCharacter(char: CharacterDTO, id : Int) : Result<CreateCharacterResponseDTO> {
        characters.add(CharacterShortDTO(characters.size, char.name, char.race.ordinal, char.characterClass.ordinal, id))
        return Result.Success(CreateCharacterResponseDTO(0))
    }
}