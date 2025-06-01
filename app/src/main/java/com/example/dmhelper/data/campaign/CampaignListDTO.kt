package com.example.dmhelper.data.campaign

import com.example.dmhelper.navigation.ScreenRoute
import kotlinx.serialization.Serializable

data class CampaignListDTO(val list: ArrayList<CampaignDTO>)

@Serializable
data class CampaignDTO(val id: Int, val name: String, val isOwner: Boolean)


fun CampaignDTO.toRoute(): ScreenRoute.CampaignMainRoute {
    return ScreenRoute.CampaignMainRoute(this.id, this.name, this.isOwner)
}