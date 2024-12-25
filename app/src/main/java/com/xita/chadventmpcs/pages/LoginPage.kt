package com.xita.chadventmpcs.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.xita.chadventmpcs.R
import com.xita.chadventmpcs.dataSource.api.RetrofitInstance
import com.xita.chadventmpcs.models.Member
import com.xita.chadventmpcs.ui.theme.Purple40
import com.xita.chadventmpcs.viewModels.LoginPageViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoginPage(navController: NavController,innerPadding: PaddingValues) {
    var  loginPageViewModel:LoginPageViewModel = viewModel()
    Box(
        Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Blue.copy(alpha = 0.5f))
        ) {


        }

        Column(
            Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            Box(
                Modifier
                    .weight(0.3f)
                    .fillMaxWidth()
            ) {

                GlideImage(
                    R.drawable.chadvent_logo, contentDescription = "", modifier = Modifier
                        .align(
                            Alignment.Center
                        )
                        .fillMaxSize(0.5f)
                )

            }

            Box(
                Modifier
                    .weight(0.7f)
                    .clip(RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp))
                    .background(Color.White)

                    .fillMaxWidth()
            ) {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    item {
                        Text(
                            "Login to account",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    items(1) {
                        OutlinedTextField(
                            value = "gyuyvyuy",
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp)
                                .background(Color.Gray.copy(alpha = 0.3f))
                        )

                        OutlinedTextField(
                            value = "pokoiyr",
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp)
                                .background(Color.Gray.copy(alpha = 0.3f))
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(40))
                                .background(Purple40)
//                                .background(if (transparent) Color.Transparent else Purple40, shape = RoundedCornerShape(40))
                                .border(3.dp, Purple40, RoundedCornerShape(40))
                                .padding(12.dp)
                                .clickable(onClick = {

                                    loginPageViewModel.getMembers()
//                                    val members = remember { mutableStateListOf<Member>() }
//                                    LaunchedEffect(Unit) {
//                                        try {
//                                            val response = RetrofitInstance.chadventAPI.getMembers(apiKey)
//                                            if (response.isSuccessful) {
//                                                members.addAll(response.body() ?: emptyList())
//                                            } else {
//                                                Log.e("API_ERROR", "Error: ${response.code()} - ${response.message()}")
//                                            }
//                                        } catch (e: Exception) {
//                                            Log.e("NETWORK_ERROR", "Exception: ${e.localizedMessage}")
//                                        }
//                                    }
                                }),

                            ) {

                            Text(
                                "Login",
                                color = Color.White,
                                modifier = Modifier
                                    .align(alignment = Alignment.Center),
                                style = TextStyle(fontSize = 25.sp)
                            )

//                            if (isLoading) CircularProgressIndicator(
//                                modifier = Modifier
//                                    .align(alignment = Alignment.Center), color = Color.White
//                            ) else Text(
//                                text,
//                                color = if (transparent) Purple40 else Color.White,
//                                modifier = Modifier
//                                    .align(alignment = Alignment.Center),
//                                style = TextStyle(fontSize = 25.sp)
//                            )
                        }

                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = false, onCheckedChange = {})
                            Text("Remember me", style = TextStyle(fontSize = 17.sp, ))
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPage(rememberNavController(), PaddingValues(0.dp))
}