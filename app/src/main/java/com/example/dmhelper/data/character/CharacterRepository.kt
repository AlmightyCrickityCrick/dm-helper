package com.example.dmhelper.data.character

import android.content.Context
import com.example.dmhelper.data.campaign.JoinCampaignDTO
import com.example.dmhelper.data.common.ApiService
import com.example.dmhelper.data.common.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import kotlin.random.Random

class CharacterRepository(private val api: ApiService, private val context: Context) {
    private var characters : ArrayList<CharacterShortDTO> = arrayListOf()
    private var allCharacter =arrayListOf<CharacterShortDTO>()

    val gson = Gson()
    var currentUserId: Int = -1

    val commonFile = "characters.json"
    val userFile: String
        get() = "${currentUserId}_characters.json"

    suspend fun fetchData() {
        val file1 = File(context.filesDir, userFile)
        val file2 = File(context.filesDir, commonFile)
        if (file2.exists()) {
            allCharacter = readCharactersFromFile(commonFile)
        }
        if (file1.exists()) {
            characters = readCharactersFromFile(userFile)
        } else {
            characters = arrayListOf()
            saveCharactersToFile()
        }
    }

    suspend fun getAllUserCharacters(id: Int): CharacterListDTO {
        currentUserId = id
        fetchData()
        return CharacterListDTO(characters)
    }

    suspend fun getCharactersById(idList : ArrayList<Int>) : CharacterListDTO {
        return CharacterListDTO(allCharacter.filter { it.id in idList } as ArrayList<CharacterShortDTO>)
    }

    suspend fun createCharacter(char: CharacterDTO, id : Int) : Result<CreateCharacterResponseDTO> {
        val charId = generateId()
        characters.add(CharacterShortDTO(charId, char.name, char.race.ordinal, char.characterClass.ordinal, id))
        allCharacter.add(CharacterShortDTO(charId, char.name, char.race.ordinal, char.characterClass.ordinal, id))
        saveCharactersToFile()
        return Result.Success(CreateCharacterResponseDTO(0))
    }

    private fun saveCharactersToFile() {
        val singleString = gson.toJson(characters)
        val fullString = gson.toJson(allCharacter)
        File(context.filesDir, userFile).writeText(singleString)
        File(context.filesDir, commonFile).writeText(fullString)
    }

    suspend fun generateId(): Int {
        return Random.nextInt(100000, 999999)
    }

    private fun readCharactersFromFile(fileName: String): ArrayList<CharacterShortDTO> {
        val file = File(context.filesDir, fileName)
        if (!file.exists() || file.readText().isBlank()) {
            return arrayListOf() // Return empty list if file is missing or empty
        }
        val json = file.readText()
        val type = object : TypeToken<ArrayList<CharacterShortDTO>>() {}.type
        return gson.fromJson(json, type)
    }
}