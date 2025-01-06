package com.xita.chadventmpcs

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.xita.chadventmpcs.dataSource.local.CHadventLocalDatabaseProvider
import com.xita.chadventmpcs.pages.LoginPage
import com.xita.chadventmpcs.pages.MainScreen
import com.xita.chadventmpcs.ui.theme.BlueBackground
import com.xita.chadventmpcs.ui.theme.ChadventMPCSTheme
import com.xita.chadventmpcs.ui.theme.Purple40
import com.xita.chadventmpcs.ui.theme.Purple80
import com.xita.chadventmpcs.viewModels.ChadventDatabaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val database = CHadventLocalDatabaseProvider.getDatabase(applicationContext)
        val memberDao = database.memberDao()
        val accountDao = database.accountDao()
        val transactionDao = database.transactionDao()

        val chadventDatabaseViewModel = ChadventDatabaseViewModel(memberDao = memberDao,accountDao = accountDao, transactionDao = transactionDao)
        setContent {
            ChadventMPCSTheme(darkTheme = false) {
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    bottomBar = { NavigationBar{
//                        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Home, contentDescription = "")
//                        })
//
//                        NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, contentDescription = "")
//                        })
//
//                        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Home, contentDescription = "")
//                        })
//                    } }
//
//                ) { innerPadding ->

//                    LoginPage(innerPadding)
//                    OnboardingScreen(onFinish = {}, paddingValues = innerPadding)
//                    )
                NavigationGraph(rememberNavController(), chadventDatabaseViewModel)
//                }
            }
        }
    }
}



@Composable
fun OnboardingScreen(navController: NavHostController, onFinish: () -> Unit) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 3 })
    val pages = listOf(
        R.raw.lottie_accounts, R.raw.lottie_contact, R.raw.lottie_news
    )

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            // Lottie animations in a pager
            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .weight(0.8f)
                .background(Color.Blue.copy(alpha = 0.1f))
                    .fillMaxWidth()
            ) { page ->
                OnboardingPage(animationResId = pages[page])
            }

            Box(Modifier.weight(0.2f)) {
                Text(
                    if (pagerState.currentPage == 0) "Easily access your account information"
                    else if (pagerState.currentPage == 1) "View and contact Chadvent members"
                    else "See important information with realtime notifications",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }


            // Navigation buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(

                    onClick = {
                    scope.launch { // Launch coroutine for scroll animation
                        if (pagerState.currentPage > 0) {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
//                if (pagerState.currentPage > 0) {
//                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
//                }
                }) {
                    Text("Previous")
                }

                Button(onClick = {
                      scope.launch {
                        if (pagerState.currentPage < pages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            onFinish()
                        }
                    }
                }) {
                    Text(if (pagerState.currentPage < pages.size - 1) "Next" else "Finish")
                }
            }
        }
    }


}

@Composable
fun OnboardingPage(animationResId: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationResId))
    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = LottieConstants.IterateForever
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(Color.Gray.copy(alpha = 0.2f))
//            .fillMaxWidth()
//            .fillMaxSize()
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
//            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_accounts))
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )

    LottieAnimation(composition, iterations = LottieConstants.IterateForever)
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    chadventDatabaseViewModel: ChadventDatabaseViewModel
) {

//    composable("splash") { SplashScreen(navController) }
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") { SplashScreen(navController) }
        composable("onboardingScreen") {
            OnboardingScreen(navController,
                { navController.navigate("login") })
        }
        composable("login") { LoginPage(navController, chadventDatabaseViewModel =  chadventDatabaseViewModel) }
        composable(route = "mainScreen") {
            MainScreen(
                navController,
                chadventDatabaseViewModel = chadventDatabaseViewModel
            )
        }
    }
}
//
//@Composable
//fun SplashScreen(navController: androidx.navigation.NavController) {
//    var isVisible by remember { mutableStateOf(false) }
////    val alpha by animateFloatAsState(targetValue = if (isVisible) 1f  else 0f)
//
//    val alpha by animateFloatAsState(
//        targetValue = if (isVisible) 1f else 0f,
//        animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing) // 2-second animation
//    )
//    val scale by animateFloatAsState(
//        targetValue = if (isVisible) 1.2f else 0.8f, // Slightly scale up
//        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
//    )
//    LaunchedEffect(Unit) {
//        isVisible = true
//        delay(4000) // 3-second delay
//        navController.navigate("onboardingScreen") {
//            popUpTo("splash") { inclusive = true }
//        }
//    }
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.fillMaxSize()
//                .background(Color.Blue.copy(alpha = 0.2f))
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.alpha(alpha)
//                    .scale(scale)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.chadvent_logo),
//                    contentDescription = "Logo",
//                    modifier = Modifier.size(100.dp)
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "Chadvent Multipurpose Coorporative society",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.padding(start = 15.dp, end = 15.dp)
//                )
//            }
//        }
//    }
//}

@Composable
fun SplashScreen(navController: androidx.navigation.NavController) {
    var isVisible by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing) // 1.5-second animation
    )
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1.2f else 0.8f, // Slightly scale up
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(Unit) {
        isVisible = true
        delay(4000) // 4-second delay
        navController.navigate("onboardingScreen") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue.copy(alpha = 0.2f))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .alpha(alpha)
                    .scale(scale)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chadvent_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Chadvent Multipurpose Cooperative Society",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp), // Ensures margin from screen edges
                    maxLines = 2, // Ensures text doesn't overflow into multiple lines unnecessarily
                    softWrap = true // Wraps text automatically
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChadventMPCSTheme {
        SplashScreen(navController = rememberNavController())
//        OnboardingScreen(navController = rememberNavController(), onFinish = {})
    }
}