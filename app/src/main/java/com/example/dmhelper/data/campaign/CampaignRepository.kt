package com.example.dmhelper.data.campaign

import androidx.compose.ui.util.trace
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.common.Util
import org.koin.core.component.KoinComponent
import kotlin.random.Random

class CampaignRepository : KoinComponent {
    private val campaignList = Util.mockCampaignList

    suspend fun getListCampaigns(id: Int): CampaignListDTO {
        return campaignList
    }

    suspend fun createCampaign(id: Int, name: String): Result<CreateCampaignResponseDTO> {
        campaignList.list.add(CampaignDTO(id, name, true))
        return Result.Success(CreateCampaignResponseDTO(campaignList.list.size))
    }

    suspend fun joinCampaign(campaignDTO: JoinCampaignDTO): Result<JoinCampaignResponseDTO> {
        val size = campaignList.list.size
        campaignList.list.add(CampaignDTO(size, "New Campaign", false))
        return Result.Success(JoinCampaignResponseDTO(size))
    }

    suspend fun getCampaignCode(id: Int): String {
        return Random.nextInt(100000, 999999).toString()
    }
}