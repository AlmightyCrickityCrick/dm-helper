package com.example.dmhelper.presentation.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmhelper.data.character.CharacterListDTO
import com.example.dmhelper.data.character.CharacterRepository
import com.example.dmhelper.data.user.UserRepository
import com.example.dmhelper.data.user.UserRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val repository: CharacterRepository,
    private val userRepository : UserRepository
) :
    ViewModel() {
    private val _characterFormState = MutableStateFlow(
        CharacterListDTO(arrayListOf())
    )
    val characterFormState: StateFlow<CharacterListDTO> = _characterFormState.asStateFlow()

    fun getCharacterList() {
        viewModelScope.launch {
            val id = userRepository.getId()
            val result = repository.getAllUserCharacters(id)
            _characterFormState.update { result }
        }
    }
}