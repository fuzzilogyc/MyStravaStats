package org.fuzz.mystravastats.data


/*
{
    "token_type": "Bearer",
    "access_token": "987654321234567898765432123456789",
    "athlete": {
    #{summary athlete representation}
},
    "refresh_token": "1234567898765432112345678987654321",
    "expires_at": 1531378346,
    "state": "STRAVA"
}
D/access_token: {"token_type":"Bearer","access_token":"30df8fde0c68aaa463d684cd8732109099366800","athlete":{"id":19310980,"username":"jieqiang_li","resource_state":2,"firstname":"Jie Qiang","lastname":"Li","city":"Wellington","state":"","country":"New Zealand","sex":"M","premium":false,"summit":false,"created_at":"2017-01-11T20:07:30Z","updated_at":"2018-11-13T09:47:36Z","badge_type_id":0,"profile_medium":"https://dgalywyr863hv.cloudfront.net/pictures/athletes/19310980/9608800/1/medium.jpg","profile":"https://dgalywyr863hv.cloudfront.net/pictures/athletes/19310980/9608800/1/large.jpg","friend":null,"follower":null,"email":"jieqiangli@gmail.com"}}
*/
class StravaOAuthTokenResponse {
    var token_type: String? = null
    lateinit var access_token: String
    var refresh_token: String? = null
    var expires_at: String? = null
    var athlete: Athlete? = null
    var state: String? = null
}