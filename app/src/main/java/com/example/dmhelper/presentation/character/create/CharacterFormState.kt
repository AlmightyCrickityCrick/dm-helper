package com.example.dmhelper.presentation.character.create

import com.example.dmhelper.data.character.Abilities
import com.example.dmhelper.data.character.ClassEnum
import com.example.dmhelper.data.character.ProficiencyLevel
import com.example.dmhelper.data.character.RaceEnum
import com.example.dmhelper.data.character.Skill
import com.example.dmhelper.presentation.common.FieldFormUiState

data class CharacterFormState(
    val name: FieldFormUiState = FieldFormUiState(),
    val background: FieldFormUiState = FieldFormUiState(),
    val hp: Int = 0,
    val level: Int = 1,
    val exp: Int = 0,
    val selectedClass: ClassEnum? = null,
    val selectedRace: RaceEnum? = null,
    val alignment: FieldFormUiState = FieldFormUiState(),
    val personalityTraits: FieldFormUiState = FieldFormUiState(),
    val ideals: FieldFormUiState = FieldFormUiState(),
    val flaws: FieldFormUiState = FieldFormUiState(),
    val abilities: AbilitiesForm = AbilitiesForm(),
    val proficiencyBonus: Int = 2,
    val skillProficiencies: Map<Skill, ProficiencyLevel> =
        Skill.entries.associateWith { ProficiencyLevel.NONE },
    val spells: List<String> = emptyList(),
    val inventory: List<String> = emptyList()
)

data class AbilitiesForm(
    val strength: FieldFormUiState = FieldFormUiState(),
    val dexterity: FieldFormUiState = FieldFormUiState(),
    val constitution: FieldFormUiState = FieldFormUiState(),
    val intelligence: FieldFormUiState = FieldFormUiState(),
    val wisdom: FieldFormUiState = FieldFormUiState(),
    val charisma: FieldFormUiState = FieldFormUiState()
)

fun AbilitiesForm.toAbilities() = Abilities(
    strength = this.strength.fieldText.toInt(),
    dexterity = this.dexterity.fieldText.toInt(),
    constitution = this.constitution.fieldText.toInt(),
    intelligence = this.intelligence.fieldText.toInt(),
    wisdom = this.wisdom.fieldText.toInt(),
    charisma = this.charisma.fieldText.toInt()
)