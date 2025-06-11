package com.example.dmhelper.presentation.session.editor

import java.util.UUID

data class MapObject(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val drawableRes: Int,
    val gridX: Int,
    val gridY: Int,
    val visible: Boolean = true,
    val canPickup: Boolean = false,
    val discoverable: Boolean = false,
    val dc: Int = 10
)