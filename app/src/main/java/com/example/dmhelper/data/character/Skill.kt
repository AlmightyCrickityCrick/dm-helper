package com.example.dmhelper.data.character

enum class Skill(val ability: AbilityType) {
    ACROBATICS(AbilityType.DEXTERITY),
    ANIMAL_HANDLING(AbilityType.WISDOM),
    ARCANA(AbilityType.INTELLIGENCE),
    ATHLETICS(AbilityType.STRENGTH),
    DECEPTION(AbilityType.CHARISMA),
    HISTORY(AbilityType.INTELLIGENCE),
    INSIGHT(AbilityType.WISDOM),
    INTIMIDATION(AbilityType.CHARISMA),
    INVESTIGATION(AbilityType.INTELLIGENCE),
    MEDICINE(AbilityType.WISDOM),
    NATURE(AbilityType.INTELLIGENCE),
    PERCEPTION(AbilityType.WISDOM),
    PERFORMANCE(AbilityType.CHARISMA),
    PERSUASION(AbilityType.CHARISMA),
    RELIGION(AbilityType.INTELLIGENCE),
    SLEIGHT_OF_HAND(AbilityType.DEXTERITY),
    STEALTH(AbilityType.DEXTERITY),
    SURVIVAL(AbilityType.WISDOM)
}

enum class ProficiencyLevel(val multiplier: Int) {
    NONE(0),
    PROFICIENT(1),
    EXPERT(2)
}

data class SkillProficiency(
    val skill: Skill,
    val proficiencyLevel: ProficiencyLevel
)