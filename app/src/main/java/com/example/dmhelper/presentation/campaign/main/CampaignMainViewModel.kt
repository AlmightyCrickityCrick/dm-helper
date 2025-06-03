package com.example.dmhelper.presentation.campaign.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmhelper.data.campaign.CampaignRepository
import com.example.dmhelper.presentation.common.FieldFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CampaignMainViewModel(
    private val repository: CampaignRepository = CampaignRepository()
) : ViewModel() {
    private val _codeFormState = MutableStateFlow(
        FieldFormUiState()
    )
    val codeFormState: StateFlow<FieldFormUiState> = _codeFormState.asStateFlow()
    var camapignId = -1

    fun setCampaignId(id: Int) {
        camapignId = id
    }

    fun getCampaignCode() {
        viewModelScope.launch {
            val result = repository.getCampaignCode(camapignId)
            _codeFormState.update { it.copy(fieldText = result) }
        }
    }
}