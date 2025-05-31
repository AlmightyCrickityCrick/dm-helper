package com.example.dmhelper.data.user

import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesDataSource constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveToken(token: String) {
        sharedPreferences.edit() {
            putString(PREF_KEY_TOKEN, token)
        }
    }

    fun getToken(): String? {
        return sharedPreferences.getString(PREF_KEY_TOKEN, null)
    }

    fun clearToken() {
        sharedPreferences.edit() {
            remove(PREF_KEY_TOKEN)
        }
    }

    companion object {
        private const val PREF_KEY_TOKEN = "JWT_TOKEN"
    }
}