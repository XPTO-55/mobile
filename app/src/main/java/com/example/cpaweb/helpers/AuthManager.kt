package com.example.cpaweb.helpers

import android.content.Context
import android.content.SharedPreferences

object AuthManager {
    private const val PREF_TOKEN = "auth_token"
    private const val PREF_USER_ID = "auth_user_id"
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

    fun saveUserInfoId(userId: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(PREF_USER_ID, userId)
        editor.apply()
    }

    fun getUserInfoId(): Long? {
        return sharedPreferences.getLong(PREF_USER_ID, 0L)
    }

    fun clearAuthToken() {
        val editor = sharedPreferences.edit()
        editor.remove(PREF_TOKEN)
        editor.remove(PREF_USER_ID)
        editor.apply()
    }

    fun isUserLoggedIn(): Boolean {
        return getAuthToken() != null
    }
}