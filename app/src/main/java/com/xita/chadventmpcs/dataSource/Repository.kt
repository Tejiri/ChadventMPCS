package com.xita.chadventmpcs.dataSource

import android.util.Log
import com.xita.chadventmpcs.dataSource.api.RetrofitInstance
import com.xita.chadventmpcs.models.Account
import com.xita.chadventmpcs.models.Member
import com.xita.chadventmpcs.models.MembersResponse
import com.xita.chadventmpcs.models.User
import com.xita.chadventmpcs.models.UserLogin
import com.xita.chadventmpcs.models.UserResponse

class Repository {
    val api_key = "5fd2ac39-15d3-4935-a36a-509597984923"

    suspend fun getMembers(): List<Member> {
        return RetrofitInstance.chadventAPI.getMembers(api_key)
    }

    suspend fun getAccounts(): List<Account> {
        return RetrofitInstance.chadventAPI.getAccounts(api_key)
    }

    suspend fun logUserIn(userLogin: UserLogin): UserResponse {
        return RetrofitInstance.chadventAPI.logUserIn(
            apiKey = api_key,
            username = userLogin.username,
            password = userLogin.password
        )
    }
}