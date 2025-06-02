package com.example.dmhelper.presentation.session.create.image

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.booksharing.presentation.mainscreen.upload.image.ImageState
import com.example.dmhelper.presentation.components.button.PrimaryButton
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

@Composable
fun ImagePicker(
    imageState: ImageState,
    onImageSelected: (Uri, String?, File) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            it?.let {
                onImageSelected.invoke(
                    it,
                    getFileExtension(context, it),
                    fileFromContentUri(context = context, contentUri = it)
                )
            }
        }
    )

    val onClick = {
        imagePicker.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }

    PrimaryButton(
        modifier = Modifier
            .offset(x = 0.dp, y = 10.dp)
            .width(100.dp),
        text = "Upload",
        isEnabled = true,
        onClick = { onClick.invoke() })
}

private fun fileFromContentUri(context: Context, contentUri: Uri): File {
    val fileExtension = getFileExtension(context, contentUri)
    val fileName = "temporary_file" + if (fileExtension != null) ".$fileExtension" else ""
    val tempFile = File(context.cacheDir, fileName)
    tempFile.createNewFile()
    try {
        val oStream = FileOutputStream(tempFile)
        val inputStream = context.contentResolver.openInputStream(contentUri)

        inputStream?.use {
            copy(inputStream, oStream)
        }
        oStream.flush()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return tempFile
}

private fun getFileExtension(context: Context, uri: Uri): String? {
    val fileType: String? = context.contentResolver.getType(uri)
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(fileType)
}

@Throws(IOException::class)
private fun copy(source: InputStream, target: OutputStream) {
    val buf = ByteArray(8192)
    var length: Int
    while (source.read(buf).also { length = it } > 0) {
        target.write(buf, 0, length)
    }
}