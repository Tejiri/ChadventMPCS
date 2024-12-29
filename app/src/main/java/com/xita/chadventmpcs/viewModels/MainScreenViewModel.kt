package com.xita.chadventmpcs.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {
    var homeSelected by mutableStateOf(true)
    var transactionsSelected by mutableStateOf(false)
    var contactsSelected by mutableStateOf(false)
    var settingsSelected by mutableStateOf(false)

    fun onHomeNavSelected() {
        homeSelected = true
        transactionsSelected = false
        contactsSelected = false
        settingsSelected = false
    }

    fun onTransactionsNavSelected() {
        transactionsSelected = true
        homeSelected = false
        contactsSelected = false
        settingsSelected = false
    }

    fun onPhoneNavSelected() {
        contactsSelected = true
        homeSelected = false
        transactionsSelected = false
        settingsSelected = false
    }

    fun onSettingsNavSelected() {
        settingsSelected = true
        homeSelected = false
        transactionsSelected = false
        contactsSelected = false
    }
}