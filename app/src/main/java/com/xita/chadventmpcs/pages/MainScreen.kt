package com.xita.chadventmpcs.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xita.chadventmpcs.R
import com.xita.chadventmpcs.viewModels.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.Blue.copy(0.2f)) {
                NavigationBarItem(selected = mainScreenViewModel.homeSelected, onClick = {
                    mainScreenViewModel.onHomeNavSelected()
                }, icon = {
                    Image(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp),
                        painter = painterResource(R.drawable.home),
                        contentDescription = "Home"
                    )
//                Icon(Icons.Default.Home, contentDescription = "")
                })

                NavigationBarItem(
                    selected = mainScreenViewModel.transactionsSelected,
                    onClick = {
                        mainScreenViewModel.onTransactionsNavSelected()
                    },
                    icon = {
                        Image(
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            painter = painterResource(R.drawable.transaction),
                            contentDescription = "Transaction",
                        )
                    })

                NavigationBarItem(selected = mainScreenViewModel.contactsSelected, onClick = {

                    mainScreenViewModel.onPhoneNavSelected()
                }, icon = {
                    Image(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp),
                        painter = painterResource(R.drawable.contact_information),
                        contentDescription = "Contact"
                    )
                })

                NavigationBarItem(
                    selected = mainScreenViewModel.settingsSelected,
                    onClick = {
                        mainScreenViewModel.onSettingsNavSelected()
                    },
                    icon = {
                        Image(
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            painter = painterResource(R.drawable.settings),
                            contentDescription = "Settings"
                        )
                    })
            }
        }) { innerPadding ->


        MainScreenContent(innerPadding, mainScreenViewModel)
    }
}

@Composable
fun MainScreenContent(innerPaddingValues: PaddingValues, mainScreenViewModel: MainScreenViewModel) {
    if (mainScreenViewModel.homeSelected) {
        HomePage(innerPaddingValues)
    } else if (mainScreenViewModel.transactionsSelected) {
        TransactionsPage(innerPaddingValues)
    } else if (mainScreenViewModel.contactsSelected) {
        ContactsPage(innerPaddingValues)
    } else {
        SettingsPage(innerPaddingValues)
    }
}

