package org.fuzz.mystravastats.data

interface LocalStore {
    fun saveOAuthToken(token: String)
    fun getOAuthToken() : String
}