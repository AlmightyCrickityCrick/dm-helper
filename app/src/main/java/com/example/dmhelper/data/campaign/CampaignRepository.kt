package com.example.dmhelper.data.campaign

import androidx.compose.ui.util.trace
import com.example.dmhelper.data.common.Result
import org.koin.core.component.KoinComponent

class CampaignRepository : KoinComponent {
    private val campaignList = Util.mockCampaignList

    suspend fun getListCampaigns(id: Int): CampaignListDTO {
        return campaignList
    }

    suspend fun createCampaign(id: Int, name: String) : Result<CreateCampaignResponseDTO>{
        campaignList.list.add(CampaignDTO(id, name, true))
        return Result.Success(CreateCampaignResponseDTO(campaignList.list.size))
    }
}