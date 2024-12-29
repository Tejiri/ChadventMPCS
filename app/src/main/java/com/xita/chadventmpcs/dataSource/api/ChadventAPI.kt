package com.xita.chadventmpcs.dataSource.api

import com.xita.chadventmpcs.models.Account
import com.xita.chadventmpcs.models.Member
import com.xita.chadventmpcs.models.MembersResponse
import com.xita.chadventmpcs.models.UserLogin
import com.xita.chadventmpcs.models.UserResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChadventAPI {

    @GET("members")
    suspend fun getMembers(@Query("api_key") apiKey: String): List<Member>

    @GET("accounts")
    suspend fun getAccounts(@Query("api_key") apiKey: String): List<Account>


    @POST("login")
    @FormUrlEncoded
    suspend fun logUserIn(
        @Query("api_key") apiKey: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): UserResponse
}