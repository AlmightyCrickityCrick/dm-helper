package com.example.dmhelper.data.campaign

import android.content.Context
import android.util.Log
import com.example.dmhelper.data.common.ApiService
import com.example.dmhelper.data.common.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.ArrayList
import kotlin.random.Random

class CampaignRepository(private val api: ApiService, private val context: Context) {
    private var campaignList = CampaignListDTO(arrayListOf())
    private var campaignCharacters =arrayListOf<JoinCampaignDTO>()
    private var fullList = CampaignListDTO(arrayListOf())

    val gson = Gson()
    var currentUserId: Int = -1

    val fileName = "campaign.json"
    val userFile: String
        get() = "${currentUserId}_campaign.json"
    fun getCharacterCampaignFileName(campaignId: Int) : String = "campaign_${campaignId}_characters.json"

    suspend fun fetchData() {
        val file1 = File(context.filesDir, userFile)
        val file2 = File(context.filesDir, fileName)
        if(file2.exists()){
            fullList = readCampaignsFromFile(fileName)
        }
        Log.d("Repository", "Reading files1")
        if (file1.exists()) {
            Log.d("Repository", "Reading files2")
            campaignList = readCampaignsFromFile(userFile)
        } else {
            campaignList = CampaignListDTO(arrayListOf())
            saveCampaignsToFile()
        }
    }

    suspend fun getListCampaigns(id: Int): CampaignListDTO {
        currentUserId = id
        Log.d("Repository", "User: ${currentUserId}, filename=${userFile}")
        fetchData()
        return campaignList
    }

    suspend fun createCampaign(id: Int, name: String): Result<CreateCampaignResponseDTO> {
        val code = getCampaignCode().toInt()
        campaignList.list.add(CampaignDTO(code, name, true))
        fullList.list.add(CampaignDTO(code, name, true))
        saveCampaignsToFile()
        return Result.Success(CreateCampaignResponseDTO(campaignList.list.size))
    }

    suspend fun joinCampaign(campaignDTO: JoinCampaignDTO): Result<JoinCampaignResponseDTO> {
        fetchData()
        val campId = campaignDTO.campaignCode.toInt()
        val camp = fullList.list.filter { it.id == campaignDTO.campaignCode.toInt() }
        Log.d("Repository", "Camp: ${camp}, fullList: ${fullList} ")
        if (camp.isNotEmpty() && camp[0] !in campaignList.list){
            campaignCharacters.add(campaignDTO)
            campaignList.list.add(CampaignDTO(campId, camp[0].name, false))
            saveCharactersToFile(campId)
            saveCampaignsToFile()
            return Result.Success(JoinCampaignResponseDTO(campId))
        }
        return Result.ServerError
    }

    suspend fun getCampaignCharacters(campaignId: Int): kotlin.collections.ArrayList<Int> {
        fetchData()
        campaignCharacters = readCharactersFromFile(campaignId)
        return campaignCharacters.filter { it.campaignCode.toInt() == campaignId }.map { it.characterId }.toCollection(ArrayList())
    }

    suspend fun getCampaignCharacterUser(campaignId: Int, userId: Int): Pair<Int?, Boolean> {
        fetchData()
        campaignCharacters = readCharactersFromFile(campaignId)
        val userCharacter = campaignCharacters.filter { it.campaignCode.toInt() == campaignId && it.id == userId}.map { it.characterId }
        return if (userCharacter.isEmpty()) Pair(null, true) else Pair(userCharacter[0], false)
    }

    suspend fun getCampaignCode(campaignId: Int? = null): String {
        Log.d("Repository", "campaignId = $campaignId")
        return campaignId?.toString() ?: Random.nextInt(100000, 999999).toString()
    }

    private fun saveCampaignsToFile() {
        val userChamp = gson.toJson(campaignList)
        val fullCamp = gson.toJson(fullList)
        File(context.filesDir, userFile).writeText(userChamp)
        File(context.filesDir, fileName).writeText(fullCamp)
    }

    private fun readCampaignsFromFile(name: String): CampaignListDTO {
        val file = File(context.filesDir, name)
        if (!file.exists() || file.readText().isBlank()) {
            Log.d("Repository", "$name is blank")
            return CampaignListDTO(arrayListOf()) // Return empty list if file is missing or empty
        }
        val json = file.readText()
        val type = object : TypeToken<CampaignListDTO>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveCharactersToFile(campaignId: Int) {
        val location = getCharacterCampaignFileName(campaignId)
        Log.d("Repository", "Saving characters to campaign $location")
        val jsonString = gson.toJson(campaignCharacters)
        File(context.filesDir, location).writeText(jsonString)
    }

    private fun readCharactersFromFile(campaignId: Int): kotlin.collections.ArrayList<JoinCampaignDTO> {
        val file = File(context.filesDir, getCharacterCampaignFileName(campaignId))
        if (!file.exists() || file.readText().isBlank()) {
            return arrayListOf()
        }
        val json = file.readText()
        val type = object : TypeToken<ArrayList<JoinCampaignDTO>>() {}.type
        return gson.fromJson(json, type)
    }
}