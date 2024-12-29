package com.xita.chadventmpcs.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ContactsScreenViewModel : ViewModel() {

    var searchText by mutableStateOf("")
    var contactPermissionGranted by   mutableStateOf(false)

    fun onSearchFieldChanged(newText: String) {
        searchText = newText
    }
}