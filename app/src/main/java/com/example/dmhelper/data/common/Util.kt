package com.example.dmhelper.data.common

import com.example.dmhelper.data.campaign.CampaignDTO
import com.example.dmhelper.data.campaign.CampaignListDTO
import com.example.dmhelper.data.character.CharacterListDTO
import com.example.dmhelper.data.character.CharacterShortDTO
import com.example.dmhelper.data.character.ClassEnum
import com.example.dmhelper.data.character.RaceEnum
import com.example.dmhelper.data.session.SessionDTO
import com.example.dmhelper.data.session.SessionListDTO

object Util {
    val mockCampaignList = CampaignListDTO(arrayListOf(
        CampaignDTO(1, "Mighty Nein", true),
        CampaignDTO(2, "Vox Machina", false),
        CampaignDTO(3, "HELLO", false)
    ))

    val mockCharacters = CharacterListDTO(
        arrayListOf(
            CharacterShortDTO(1, "Eliria", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal),
            CharacterShortDTO(2, "TEST", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal),
            CharacterShortDTO(3, "TTT", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal),
            CharacterShortDTO(4, "Hello", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal),
        )
    )

    val mockSessions = SessionListDTO(
        arrayListOf(
            SessionDTO(1, "Sessions 1", true),
            SessionDTO(2, "Sessions 2", true),
            SessionDTO(3, "Sessions 3", false),
            SessionDTO(4, "Sessions 4", false),
            SessionDTO(5, "Sessions 5", true),
        )
    )
}