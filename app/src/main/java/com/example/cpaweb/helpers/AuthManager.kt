package com.example.cpaweb.helpers

import android.content.Context
import android.content.SharedPreferences

object AuthManager {
    private const val PREF_TOKEN = "auth_token"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
    }

    fun saveAuthToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(PREF_TOKEN, token)
        editor.apply()
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString(PREF_TOKEN, null)
    }

    fun clearAuthToken() {
        val editor = sharedPreferences.edit()
        editor.remove(PREF_TOKEN)
        editor.apply()
    }

    fun isUserLoggedIn(): Boolean {
        return getAuthToken() != null
    }
}