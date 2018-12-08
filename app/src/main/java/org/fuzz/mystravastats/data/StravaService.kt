package org.fuzz.mystravastats.data

import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface StravaService {

    @FormUrlEncoded
    @POST("oauth/token")
    fun getOAuthToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ) : Deferred<StravaOAuthTokenResponse>

    @GET("api/v3/athlete")
    fun getAuthenticatedAthelete(@Header("Authorization") authToken: String) : Deferred<Athlete>

}