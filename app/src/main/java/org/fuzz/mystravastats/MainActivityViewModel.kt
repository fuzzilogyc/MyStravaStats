package org.fuzz.mystravastats

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.fuzz.mystravastats.auth.OAuthRepository

class MainActivityViewModel(private val repo: OAuthRepository) : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doAuth(clientId: String, clientSecret: String, code: String?) {
        if (code != null && code.isNotEmpty()) {
            uiScope.launch {
                val accessToken = repo.getOAuthAccessToken(clientId, clientSecret, code)
                if (accessToken.isNotEmpty()) {
                    val athlete = repo.getAuthenticatedAthlete("Bearer " + accessToken)
                    if (athlete != null) {
                        Log.d("athlete", "" + athlete.id)
                        Log.d("athlete", "" + athlete.firstname)
                        Log.d("athlete", "" + athlete.lastname)
                        Log.d("athlete", "" + athlete.username)
                    }
                }
            }
        } else {
        }
    }

}