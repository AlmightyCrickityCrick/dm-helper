package com.example.dmhelper.presentation.session.editor

import androidx.lifecycle.ViewModel
import com.example.dmhelper.data.campaign.CampaignRepository
import com.example.dmhelper.data.session.SessionRepository

class SessionEditorViewModel(val repository: SessionRepository, val campaignRepository: CampaignRepository) : ViewModel() {


    fun onUiEvent(event: SessionEditorUiEvent) {
        when (event) {
            is SessionEditorUiEvent.RemoveObject -> {
                // remove object logic
            }
            is SessionEditorUiEvent.ChangeVisibility -> {
                // find object by ID and change visibility
            }
            is SessionEditorUiEvent.ChangeDC -> {
                // find object and update DC
            }
            is SessionEditorUiEvent.ChangePickupability -> {
                // update pickupable flag
            }
            is SessionEditorUiEvent.ChangeDiscoverability -> {
                // update discoverability flag
            }
        }
    }

}