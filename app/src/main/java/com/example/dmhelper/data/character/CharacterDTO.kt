package com.example.dmhelper.data.character

data class CharacterDTO(
    val name: String,
    val background: String,
    val hp: Int,
    val level: Int,
    val exp: Int,
    val characterClass: ClassEnum,
    val race: RaceEnum,
    val alignment: String,
    val personalityTraits: String,
    val ideals: String,
    val flaws: String,
    val abilities: Abilities,
    val proficiencyBonus: Int,
    val skills: List<SkillProficiency>,
    val spells: List<String>,
    val inventory: List<String>
)

data class CreateCharacterResponseDTO(val id: Int)


