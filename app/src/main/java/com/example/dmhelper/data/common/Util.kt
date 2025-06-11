package com.example.dmhelper.data.common

import com.example.dmhelper.data.campaign.CampaignDTO
import com.example.dmhelper.data.campaign.CampaignListDTO
import com.example.dmhelper.data.character.CharacterListDTO
import com.example.dmhelper.data.character.CharacterShortDTO
import com.example.dmhelper.data.character.ClassEnum
import com.example.dmhelper.data.character.RaceEnum
import com.example.dmhelper.data.session.SessionShortDTO
import com.example.dmhelper.data.session.SessionListDTO

object Util {
    val mockCampaignList = CampaignListDTO(arrayListOf(
        CampaignDTO(1, "Mighty Nein", true),
        CampaignDTO(2, "Vox Machina", false),
        CampaignDTO(3, "HELLO", false)
    ))

    val mockCharacters = CharacterListDTO(
        arrayListOf(
            CharacterShortDTO(1, "Eliria", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal, 1),
            CharacterShortDTO(2, "TEST", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal, 2),
            CharacterShortDTO(3, "TTT", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal, 1),
            CharacterShortDTO(4, "Hello", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal, 3),
        )
    )

//    val mockSessions = SessionListDTO(
//        arrayListOf(
//            SessionShortDTO(1, "Sessions 1", true),
//            SessionShortDTO(2, "Sessions 2", true),
//            SessionShortDTO(3, "Sessions 3", false),
//            SessionShortDTO(4, "Sessions 4", false),
//            SessionShortDTO(5, "Sessions 5", true),
//        )
//    )
}