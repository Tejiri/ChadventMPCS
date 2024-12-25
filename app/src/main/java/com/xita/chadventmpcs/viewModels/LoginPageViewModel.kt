package com.xita.chadventmpcs.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xita.chadventmpcs.dataSource.Repository
import com.xita.chadventmpcs.models.Member
import kotlinx.coroutines.launch

class LoginPageViewModel:ViewModel() {
    private var repository = Repository()
    var membersResponse = mutableStateListOf<Member>()

    init {

    }

   fun getMembers(){
        viewModelScope.launch {
            try {
                val response =  repository.getMembers()
                membersResponse.clear()
                membersResponse.addAll(response)

                for (i in membersResponse){
                    Log.i("MYINFO", i.toString())
                }
            } catch (e: Exception) {
                Log.i("MYEXCEPTION", e.localizedMessage)
            } finally {

            }

        }
    }
}