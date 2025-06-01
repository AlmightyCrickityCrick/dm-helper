package com.example.dmhelper.data.campaign

class CampaignRepository {
    suspend fun getListCampaigns(id: Int): CampaignListDTO {
        return Util.mockCampaignList
    }
}