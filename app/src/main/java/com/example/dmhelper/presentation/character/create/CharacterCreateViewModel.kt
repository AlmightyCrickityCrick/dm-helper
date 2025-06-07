package com.example.dmhelper.presentation.character.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmhelper.data.character.AbilityType
import com.example.dmhelper.data.character.CharacterDTO
import com.example.dmhelper.data.character.CharacterRepository
import com.example.dmhelper.data.character.ClassEnum
import com.example.dmhelper.data.character.CreateCharacterResponseDTO
import com.example.dmhelper.data.character.ProficiencyLevel
import com.example.dmhelper.data.character.RaceEnum
import com.example.dmhelper.data.character.Skill
import com.example.dmhelper.data.character.SkillProficiency
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.user.UserRepository
import com.example.dmhelper.data.user.UserRepositoryImpl
import com.example.dmhelper.data.user.dto.LoginResponseDTO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterCreateViewModel(
    val repository: CharacterRepository = CharacterRepository(),
    val userRepository: UserRepository = UserRepositoryImpl()
) : ViewModel() {
    private val _formState = MutableStateFlow(CharacterFormState())
    val formState: StateFlow<CharacterFormState> = _formState

    private val _eventChannel = Channel<Result<CreateCharacterResponseDTO>>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onEvent(event: CharacterCreationEvent) {
        when (event) {
            is CharacterCreationEvent.NameChanged -> onNameChanged(event.name)
            is CharacterCreationEvent.BackgroundChanged -> onBackgroundChanged(event.background)
            is CharacterCreationEvent.HPChanged -> onHPChanged(event.hp)
            is CharacterCreationEvent.LevelChanged -> onLevelChanged(event.level)
            is CharacterCreationEvent.EXPChanged -> onExpChanged(event.exp)
            is CharacterCreationEvent.ClassChanged -> onClassChanged(event.characterClass)
            is CharacterCreationEvent.RaceChanged -> onRaceChanged(event.characterRace)
            is CharacterCreationEvent.AlignmentChanged -> onAlignmentChanged(event.alignment)
            is CharacterCreationEvent.PersonalityTraitsChanged -> onPersonalityTraitsChanged(event.traits)
            is CharacterCreationEvent.IdealsChanged -> onIdealsChanged(event.ideals)
            is CharacterCreationEvent.FlawsChanged -> onFlawsChanged(event.flaws)
            is CharacterCreationEvent.AbilityChanged -> onAbilityChanged(event.type, event.score)
            is CharacterCreationEvent.SkillProficiencyChanged -> onSkillProficiencyChanged(event.skill)
            is CharacterCreationEvent.AddSpell -> onAddSpell(event.spell)
            is CharacterCreationEvent.RemoveSpell -> onRemoveSpell(event.spell)
            is CharacterCreationEvent.AddInventoryItem -> onAddInventoryItem(event.item)
            is CharacterCreationEvent.RemoveInventoryItem -> onRemoveInventoryItem(event.item)
        }
    }

    fun createCharacter(){
        viewModelScope.launch {
            val id = userRepository.getId()
            val result = repository.createCharacter(toCharacterDto(), id)
            _eventChannel.send(result)
        }
    }

    private fun onNameChanged(name: String) {
        _formState.update { it.copy(name = it.name.copy(name)) }
    }

    private fun onBackgroundChanged(background: String) {
        _formState.update { it.copy(background = it.background.copy(background)) }
    }

    private fun onHPChanged(hp: Int) {
        _formState.update { it.copy(hp = hp) }
    }

    private fun onLevelChanged(level: Int) {
        _formState.update {
            it.copy(
                level = level,
                proficiencyBonus = calculateProficiencyBonus(level)
            )
        }
    }

    private fun onExpChanged(exp: Int) {
        _formState.update { it.copy(exp = exp) }
    }

    private fun onClassChanged(characterClass: ClassEnum) {
        _formState.update { it.copy(selectedClass = characterClass) }
    }

    private fun onRaceChanged(race: RaceEnum) {
        _formState.update { it.copy(selectedRace = race) }
    }

    private fun onAlignmentChanged(alignment: String) {
        _formState.update { it.copy(alignment = it.alignment.copy(alignment)) }
    }

    private fun onPersonalityTraitsChanged(traits: String) {
        _formState.update { it.copy(personalityTraits = it.personalityTraits.copy(traits)) }
    }

    private fun onIdealsChanged(ideals: String) {
        _formState.update { it.copy(ideals = it.ideals.copy(ideals)) }
    }

    private fun onFlawsChanged(flaws: String) {
        _formState.update { it.copy(flaws = it.flaws.copy(flaws)) }
    }

    private fun onAbilityChanged(type: AbilityType, score: Int) {
        _formState.update {
            val updatedAbilities = when (type) {
                AbilityType.STRENGTH -> it.abilities.copy(strength = it.abilities.strength.copy(score.toString()))
                AbilityType.DEXTERITY -> it.abilities.copy(dexterity = it.abilities.dexterity.copy(score.toString()))
                AbilityType.CONSTITUTION -> it.abilities.copy(constitution = it.abilities.constitution.copy(score.toString()))
                AbilityType.INTELLIGENCE -> it.abilities.copy(intelligence = it.abilities.intelligence.copy(score.toString()))
                AbilityType.WISDOM -> it.abilities.copy(wisdom = it.abilities.wisdom.copy(score.toString()))
                AbilityType.CHARISMA -> it.abilities.copy(charisma = it.abilities.charisma.copy(score.toString()))
            }
            it.copy(abilities = updatedAbilities)
        }
    }

    fun getAbilityForm(type: AbilityType) = when (type) {
        AbilityType.STRENGTH -> formState.value.abilities.strength
        AbilityType.DEXTERITY -> formState.value.abilities.dexterity
        AbilityType.CONSTITUTION -> formState.value.abilities.constitution
        AbilityType.INTELLIGENCE -> formState.value.abilities.intelligence
        AbilityType.WISDOM -> formState.value.abilities.wisdom
        AbilityType.CHARISMA -> formState.value.abilities.charisma
    }

    fun onSkillProficiencyChanged(skill: Skill) {
        _formState.update {
            val updatedSkills = it.skillProficiencies.toMutableMap()
            updatedSkills[skill] = when (it.skillProficiencies[skill]) {
                ProficiencyLevel.NONE -> ProficiencyLevel.PROFICIENT
                ProficiencyLevel.PROFICIENT -> ProficiencyLevel.EXPERT
                ProficiencyLevel.EXPERT -> ProficiencyLevel.NONE
                null -> ProficiencyLevel.PROFICIENT
            }
            it.copy(skillProficiencies = updatedSkills)
        }
    }

    fun onAddSpell(spell: String) {
        _formState.update {
            if (!it.spells.contains(spell)) it.copy(spells = it.spells + spell)
            else it
        }
    }

    fun onRemoveSpell(spell: String) {
        _formState.update {
            it.copy(spells = it.spells - spell)
        }
    }

    fun onAddInventoryItem(item: String) {
        _formState.update {
            it.copy(inventory = it.inventory + item)
        }
    }

    fun onRemoveInventoryItem(item: String) {
        _formState.update {
            it.copy(inventory = it.inventory - item)
        }
    }

    fun toCharacterDto(): CharacterDTO {
        val state = formState.value
        val charClass = state.selectedClass?: ClassEnum.BARBARIAN
        val race = state.selectedRace ?: RaceEnum.HUMAN

        val skills = state.skillProficiencies.map { (skill, level) ->
            SkillProficiency(skill, level)
        }
        return CharacterDTO(
            name = state.name.fieldText,
            background = state.background.fieldText,
            hp = state.hp,
            level = state.level,
            exp = state.exp,
            characterClass = charClass,
            race = race,
            alignment = state.alignment.fieldText,
            personalityTraits = state.personalityTraits.fieldText,
            ideals = state.ideals.fieldText,
            flaws = state.flaws.fieldText,
            abilities = state.abilities.toAbilities(),
            proficiencyBonus = state.proficiencyBonus,
            skills = skills,
            spells = state.spells,
            inventory = state.inventory
        )
    }

    private fun calculateProficiencyBonus(level: Int): Int {
        return 2 + ((level - 1) / 4)
    }
}