package org.fuzz.mystravastats.data

import android.content.SharedPreferences

class SharedPreferencesLocalStore(private val sharedPreferences: SharedPreferences) : LocalStore {

    companion object {
        const val OAUTH_TOKEN_KEY = "oauth_token"
    }

    override fun saveOAuthToken(token: String) {
        sharedPreferences.edit().putString(OAUTH_TOKEN_KEY, token).apply()
    }

    override fun getOAuthToken(): String {
        return sharedPreferences.getString(OAUTH_TOKEN_KEY, null) ?: ""
    }

}