package org.fuzz.mystravastats.auth

import android.util.Log
import org.fuzz.mystravastats.data.Athlete
import org.fuzz.mystravastats.data.LocalStore
import org.fuzz.mystravastats.data.StravaService

class OAuthRepository(private val stravaService: StravaService, private val localStore: LocalStore) {

    suspend fun getOAuthAccessToken(clientId: String, clientSecret: String, code: String): String {
        val tokenFromLocal = getOAuthTokenFromLocal()

        return if (tokenFromLocal.isNotEmpty()) {
            // get straight from local
            tokenFromLocal
        } else {
            getOAuthTokenFromRemote(clientId, clientSecret, code)
        }
    }

    private suspend fun getOAuthTokenFromRemote(clientId: String, clientSecret: String, code: String): String {
        val result = stravaService.getOAuthToken(clientId, clientSecret, code).await()
        val accessToken = result.access_token
        Log.d("access_token", "" + accessToken)
        saveOAuthToken(accessToken)
        return accessToken
    }

    suspend fun getAuthenticatedAthlete(accessToken: String): Athlete? {
        return stravaService.getAuthenticatedAthelete(accessToken).await()
    }

    private fun getOAuthTokenFromLocal(): String {
        return localStore.getOAuthToken()
    }

    private fun saveOAuthToken(token: String) {
        localStore.saveOAuthToken(token)
    }

}
