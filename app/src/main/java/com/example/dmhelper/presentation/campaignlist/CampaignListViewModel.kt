package com.example.dmhelper.presentation.campaignlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmhelper.data.campaign.CampaignListDTO
import com.example.dmhelper.data.campaign.CampaignRepository
import com.example.dmhelper.data.campaign.CreateCampaignResponseDTO
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.user.UserRepository
import com.example.dmhelper.data.user.UserRepositoryImpl
import com.example.dmhelper.presentation.common.FieldFormUiState
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
    private val _campaignFormState = MutableStateFlow(FieldFormUiState())
    val campaignFormState: StateFlow<FieldFormUiState> = _campaignFormState.asStateFlow()
    private val _campaignListState = MutableStateFlow(CampaignListDTO(arrayListOf()))
    val campaignListState: StateFlow<CampaignListDTO> = _campaignListState.asStateFlow()
    private var id : Int = -1

    fun onCampaignFieldChanged(newValue: String) {
        _campaignFormState.update { previous ->
            previous.copy(
                fieldText = newValue
            )
        }
    }

    fun createCampaign(){
        viewModelScope.launch {
            val result : Result<CreateCampaignResponseDTO> = repository.createCampaign(id, campaignFormState.value.fieldText)
            if (result is Result.Success) {
                onCampaignFieldChanged("")
                getCampaignList()
            }
        }
    }

    fun getCampaignList() {
        viewModelScope.launch {
            id = userRepository.getId()
            val result = repository.getListCampaigns(id)
            _campaignListState.update { result }
        }
    }
}