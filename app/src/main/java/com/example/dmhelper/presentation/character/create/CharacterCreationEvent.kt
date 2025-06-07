package com.example.dmhelper.presentation.character.create

import com.example.dmhelper.data.character.AbilityType
import com.example.dmhelper.data.character.ClassEnum
import com.example.dmhelper.data.character.ProficiencyLevel
import com.example.dmhelper.data.character.RaceEnum
import com.example.dmhelper.data.character.Skill

sealed class CharacterCreationEvent {
    data class NameChanged(val name: String) : CharacterCreationEvent()
    data class BackgroundChanged(val background: String) : CharacterCreationEvent()
    data class HPChanged(val hp: Int) : CharacterCreationEvent()
    data class LevelChanged(val level: Int) : CharacterCreationEvent()
    data class EXPChanged(val exp: Int) : CharacterCreationEvent()
    data class ClassChanged(val characterClass: ClassEnum) : CharacterCreationEvent()
    data class RaceChanged(val characterRace: RaceEnum) : CharacterCreationEvent()
    data class AlignmentChanged(val alignment: String) : CharacterCreationEvent()
    data class PersonalityTraitsChanged(val traits: String) : CharacterCreationEvent()
    data class IdealsChanged(val ideals: String) : CharacterCreationEvent()
    data class FlawsChanged(val flaws: String) : CharacterCreationEvent()
    data class AbilityChanged(val type: AbilityType, val score: Int) : CharacterCreationEvent()
    data class SkillProficiencyChanged(val skill: Skill) : CharacterCreationEvent()
    data class AddSpell(val spell: String) : CharacterCreationEvent()
    data class RemoveSpell(val spell: String) : CharacterCreationEvent()
    data class AddInventoryItem(val item: String) : CharacterCreationEvent()
    data class RemoveInventoryItem(val item: String) : CharacterCreationEvent()

}