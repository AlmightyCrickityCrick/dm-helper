package com.example.dmhelper.data.session

import com.example.dmhelper.data.campaign.CampaignDTO
import com.example.dmhelper.navigation.ScreenRoute

data class SessionListDTO(val list: ArrayList<SessionShortDTO>)

data class SessionShortDTO(val id: Int, val name: String,  val map: String, val isAccessible: Boolean)


fun SessionShortDTO.toRoute(): ScreenRoute.SessionEditorRoute {
    return ScreenRoute.SessionEditorRoute(this.id, this.map)
}
