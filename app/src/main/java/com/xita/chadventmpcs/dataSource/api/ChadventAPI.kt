package com.xita.chadventmpcs.dataSource.api

import com.xita.chadventmpcs.models.Member
import com.xita.chadventmpcs.models.MembersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ChadventAPI {

    @GET("members")
    suspend fun getMembers(@Query("api_key") apiKey: String): List<Member>
}