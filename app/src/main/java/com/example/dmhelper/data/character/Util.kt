package com.example.dmhelper.data.character

object Util {

    val mockCharacters = CharacterListDTO(
        arrayListOf(
            CharacterShortDTO(1, "Eliria", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal),
            CharacterShortDTO(2, "TEST", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal),
            CharacterShortDTO(3, "TTT", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal),
            CharacterShortDTO(4, "Hello", RaceEnum.HUMAN.ordinal, ClassEnum.ROGUE.ordinal),
        )
    )
}