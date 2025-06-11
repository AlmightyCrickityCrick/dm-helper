package com.example.dmhelper.presentation.session.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmhelper.data.campaign.CampaignRepository
import com.example.dmhelper.data.character.CharacterRepository
import com.example.dmhelper.data.session.SessionListDTO
import com.example.dmhelper.data.session.SessionRepository
import com.example.dmhelper.data.user.UserRepository
import com.example.dmhelper.data.user.UserRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SessionListViewModel(
    private val repository: SessionRepository,
    private val campaignRepository: CampaignRepository,
    private val userRepository : UserRepository
) :
    ViewModel() {
    private val _sessionFormState = MutableStateFlow(
        SessionListDTO(arrayListOf())
    )
    val sessionFormState: StateFlow<SessionListDTO> = _sessionFormState.asStateFlow()

    fun getSessionList(campaignId: Int) {
        viewModelScope.launch {
            val id = userRepository.getId()
            val (character, isDm )= campaignRepository.getCampaignCharacterUser(campaignId, id)
            val result = repository.getSessions(character, campaignId, isDm)
            _sessionFormState.update { result }
        }
    }
}