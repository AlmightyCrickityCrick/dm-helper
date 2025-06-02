package com.example.booksharing.presentation.mainscreen.upload.image

import android.net.Uri
import java.io.File

data class ImageState(
    val imageUri: Uri? = null,
    val isSelected: Boolean = false,
    val isValid: Boolean = false,
    val imageType: String? = null,
    val imageFile: File? = null
)