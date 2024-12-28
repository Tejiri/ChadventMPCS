package com.xita.chadventmpcs.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xita.chadventmpcs.dataSource.Repository
import com.xita.chadventmpcs.models.Member
import com.xita.chadventmpcs.models.User
import com.xita.chadventmpcs.models.UserLogin
import kotlinx.coroutines.launch

class LoginPageViewModel : ViewModel() {
    private var repository = Repository()
    var membersResponse = mutableStateListOf<Member>()
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)


//    init {
//        getMembers()
//    }

    fun usernameChange(newUsername: String) {
        username = newUsername
    }

    fun passwordChange(newPassword: String) {
        password = newPassword
    }

    fun logUserIn(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.logUserIn(UserLogin(username, password))
                val loggedInUser: User? = response.user

                if (loggedInUser == null) {
                    getMembers(onSuccess)
                }
                Log.i("MYINFO", response.toString())
            } catch (e: Exception) {
                Log.i("MYEXCEPTION", e.localizedMessage)
            } finally {

            }
        }
    }

    fun getMembers(onSuccess: () -> Unit) {

        viewModelScope.launch {
            try {
                val response = repository.getMembers()
                membersResponse.clear()
                membersResponse.addAll(response)
onSuccess()
                for (i in membersResponse) {
//                    Log.i("MYINFO", i.toString())
                }
            } catch (e: Exception) {
                Log.i("MYEXCEPTION", e.localizedMessage)
            } finally {

            }

        }
    }
}