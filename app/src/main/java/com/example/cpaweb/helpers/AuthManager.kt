package com.example.cpaweb.helpers

import android.content.Context
import android.content.SharedPreferences
import android.service.autofill.UserData
import com.example.cpaweb.models.auth.LoginResponse
import com.example.cpaweb.models.users.UserBase

object AuthManager {
    private const val PREF_TOKEN = "auth_token"
    private const val PREF_USER_ID = "auth_user_id"
    private const val PREF_USER_NAME = "auth_user_name"
    private const val PREF_USER_TYPE = "auth_user_type"
    private const val PREF_USER_EMAIL = "auth_user_email"
    private const val PREF_USER_PHONE = "auth_user_phone"
    private const val PREF_USER_PROFILE_URL = "auth_user_profile_url"
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

    fun saveUser(userData: LoginResponse){
        val editor = sharedPreferences.edit()
        editor.putLong(PREF_USER_ID, userData.id)
        editor.putString(PREF_USER_NAME, userData.username)
        editor.putString(PREF_USER_EMAIL, userData.email)
        editor.putString(PREF_USER_TYPE, userData.userType)
        editor.putString(PREF_USER_PHONE, userData.phone)
        editor.putString(PREF_USER_PROFILE_URL, userData.profileUrl)
        editor.apply()
    }

    fun getUser(): LoginResponse {
        val userId = sharedPreferences.getLong(PREF_USER_ID, 0L)
        val userName = sharedPreferences.getString(PREF_USER_NAME, "")
        val userType = sharedPreferences.getString(PREF_USER_TYPE, "")
        val userEmail = sharedPreferences.getString(PREF_USER_EMAIL, "")
        val userPhone = sharedPreferences.getString(PREF_USER_PHONE, "")
        val userProfileUrl = sharedPreferences.getString(PREF_USER_PROFILE_URL, "")

        return LoginResponse(
            id = userId,
            username = userName.toString(),
            userType = userType.toString(),
            email = userEmail.toString(),
            phone = userPhone.toString(),
            profileUrl = userProfileUrl?.toString()
        )
    }

    fun getUserInfoId(): Long? {
        return sharedPreferences.getLong(PREF_USER_ID, 0L)
    }

    fun getUserType(): String? {
        return sharedPreferences.getString(PREF_USER_TYPE, "patient")
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