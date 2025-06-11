package com.example.dmhelper.presentation.session.editor

import androidx.lifecycle.ViewModel
import com.example.dmhelper.R
import com.example.dmhelper.data.campaign.CampaignRepository
import com.example.dmhelper.data.session.SessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SessionEditorViewModel(val repository: SessionRepository, val campaignRepository: CampaignRepository) : ViewModel() {
    val availableObjects: StateFlow<List<MapObjectTemplate>> get() = _availableObjects
    val placedObjects: StateFlow<List<MapObject>> get() = _placedObjects
    val name: StateFlow<String> get() = _name

    // 2. Backing mutable state
    private val _availableObjects = MutableStateFlow(
        listOf(
            MapObjectTemplate("rogue", R.drawable.ic_rogue),
            MapObjectTemplate("fighter", R.drawable.ic_fighter)
        )
    )

    private val _placedObjects = MutableStateFlow<List<MapObject>>(emptyList())
    private val _name = MutableStateFlow("Objects")

    // 3. Expose methods to update state
    fun addPlacedObject(obj: MapObject) {
        _placedObjects.value = _placedObjects.value + obj
    }

    fun removePlacedObject(obj: MapObject) {
        _placedObjects.value = _placedObjects.value - obj
    }

    fun setName(newName: String) {
        _name.value = newName
    }

    fun resetPlacedObjects() {
        _placedObjects.value = emptyList()
    }

    fun onUiEvent(event: SessionEditorUiEvent) {
        when (event) {
            is SessionEditorUiEvent.RemoveObject -> {
                _placedObjects.value = _placedObjects.value.filterNot { it.id == event.objectId }
            }

            is SessionEditorUiEvent.ChangeVisibility -> {
                _placedObjects.update { list ->
                    list.map { obj ->
                        if (obj.id == event.objectId) obj.copy(visible = event.isVisible) else obj
                    }
                }
            }

            is SessionEditorUiEvent.ChangeDC -> {
                _placedObjects.update { list ->
                    list.map { obj ->
                        if (obj.id == event.objectId) obj.copy(dc = event.newDC) else obj
                    }
                }
            }

            is SessionEditorUiEvent.ChangePickupability -> {
                _placedObjects.update { list ->
                    list.map { obj ->
                        if (obj.id == event.objectId) obj.copy(canPickup = event.isPickupable) else obj
                    }
                }
            }

            is SessionEditorUiEvent.ChangeDiscoverability -> {
                _placedObjects.update { list ->
                    list.map { obj ->
                        if (obj.id == event.objectId) obj.copy(discoverable = event.isDiscoverable) else obj
                    }
                }
            }
        }
    }

}