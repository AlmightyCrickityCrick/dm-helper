package com.example.dmhelper.data.common

import com.example.dmhelper.data.campaign.CampaignListDTO
import com.example.dmhelper.data.campaign.CreateCampaignDTO
import com.example.dmhelper.data.campaign.CreateCampaignResponseDTO
import com.example.dmhelper.data.campaign.JoinCampaignDTO
import com.example.dmhelper.data.campaign.JoinCampaignResponseDTO
import com.example.dmhelper.data.character.CharacterDTO
import com.example.dmhelper.data.character.CharacterListDTO
import com.example.dmhelper.data.character.CreateCharacterResponseDTO
import com.example.dmhelper.data.session.CreateSessionDTO
import com.example.dmhelper.data.session.CreateSessionResponseDTO
import com.example.dmhelper.data.session.SessionListDTO
import com.example.dmhelper.data.user.dto.AuthDTO
import com.example.dmhelper.data.user.dto.LoginResponseDTO
import com.example.dmhelper.data.user.dto.RegisterDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Campaigns
    @GET("campaigns/{id}")
    suspend fun getListCampaigns(@Path("id") id: Int): CampaignListDTO

    @POST("campaigns/create")
    suspend fun createCampaign(
        @Query("id") id: Int,
        @Query("name") name: String
    ): Result<CreateCampaignResponseDTO>

    @POST("campaigns/join")
    suspend fun joinCampaign(@Body campaignDTO: JoinCampaignDTO): Result<JoinCampaignResponseDTO>

    @GET("campaigns/{id}/code")
    suspend fun getCampaignCode(@Path("id") id: Int): String

    // Characters
    @GET("users/{id}/characters")
    suspend fun getAllUserCharacters(@Path("id") id: Int): CharacterListDTO

    @GET("campaigns/{id}/characters")
    suspend fun getAllCampaignCharacters(@Path("id") id: Int): CharacterListDTO

    @POST("characters/create")
    suspend fun createCharacter(
        @Body char: CharacterDTO,
        @Query("campaignId") id: Int
    ): Result<CreateCharacterResponseDTO>

    // Sessions
    @GET("campaigns/{campaignId}/sessions")
    suspend fun getSessions(
        @Query("userId") userId: Int,
        @Path("campaignId") campaignId: Int
    ): SessionListDTO

    @POST("sessions/create")
    suspend fun createSession(@Body session: CreateSessionDTO): Result<CreateSessionResponseDTO>

    // File Upload
    @Multipart
    @POST("maps/upload")
    suspend fun uploadMap(
        @Part uri: MultipartBody.Part,
        @Part("type") type: RequestBody
    ): String

    // Auth
    @POST("auth/login")
    suspend fun login(@Body authDTO: AuthDTO): Result<LoginResponseDTO>

    @POST("auth/register")
    suspend fun register(@Body registerDTO: RegisterDTO): Result<LoginResponseDTO>

    @POST("auth/signout")
    suspend fun signout(): Result<Boolean>
}