package com.xita.chadventmpcs.dataSource.api

import com.xita.chadventmpcs.models.Member
import com.xita.chadventmpcs.models.MembersResponse
import com.xita.chadventmpcs.models.UserLogin
import com.xita.chadventmpcs.models.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChadventAPI {

    @GET("members")
    suspend fun getMembers(@Query("api_key") apiKey: String): List<Member>

    @GET("accounts")
    suspend fun getAccounts(@Query("api_key") apiKey: String): List<Member>

    @POST("login")
    suspend fun logUserIn(
        @Query("api_key") apiKey: String,
        @Body userLogin: UserLogin
    ): UserResponse
}