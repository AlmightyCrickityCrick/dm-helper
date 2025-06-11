package com.example.dmhelper.presentation.session.editor

import com.example.dmhelper.data.session.DifficultyClass

sealed class SessionEditorUiEvent {
    data class RemoveObject(val objectId: String) : SessionEditorUiEvent()
    data class ChangeVisibility(val objectId: String, val isVisible: Boolean) : SessionEditorUiEvent()
    data class ChangeDC(val objectId: String, val newDC: DifficultyClass) : SessionEditorUiEvent()
    data class ChangePickupability(val objectId: String, val isPickupable: Boolean) : SessionEditorUiEvent()
    data class ChangeDiscoverability(val objectId: String, val isDiscoverable: Boolean) : SessionEditorUiEvent()
}