package com.example.dmhelper.data.campaign

data class CampaignListDTO(val list: ArrayList<CampaignDTO>)

data class CampaignDTO(val id: Int, val name: String, val isOwner: Boolean)
