package com.example.dmhelper.presentation.campaignlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmhelper.data.campaign.CampaignListDTO
import com.example.dmhelper.data.campaign.CampaignRepository
import com.example.dmhelper.data.user.UserRepository
import com.example.dmhelper.data.user.UserRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CampaignListViewModel(
    private val repository: CampaignRepository = CampaignRepository(),
    private val userRepository: UserRepository = UserRepositoryImpl()
) :
    ViewModel() {
    private val _campaignFormState = MutableStateFlow(
        CampaignListDTO(arrayListOf())
    )
    val campaignFormState: StateFlow<CampaignListDTO> = _campaignFormState.asStateFlow()

    fun getCampaignList() {
        viewModelScope.launch {
            val id = userRepository.getId()
            val result = repository.getListCampaigns(id)
            _campaignFormState.update { result }
        }
    }
}