package com.xita.chadventmpcs.dataSource

import com.xita.chadventmpcs.dataSource.api.RetrofitInstance
import com.xita.chadventmpcs.models.Member
import com.xita.chadventmpcs.models.MembersResponse

class Repository {

    suspend fun getMembers(): List<Member> {
        val api_key = "5fd2ac39-15d3-4935-a36a-509597984923"
        return RetrofitInstance.chadventAPI.getMembers(api_key)
    }
}