package com.xita.chadventmpcs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.xita.chadventmpcs.ui.theme.ChadventMPCSTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChadventMPCSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OnboardingScreen(onFinish = { }, paddingValues = innerPadding)
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingScreen(onFinish: () -> Unit, paddingValues: PaddingValues) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 3 })
    val pages = listOf(
        R.raw.lottie_accounts,
        R.raw.lottie_contact,
        R.raw.lottie_news
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Lottie animations in a pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(0.8f)
//                .background(Color.Blue)
                .fillMaxWidth()
        ) { page ->
            OnboardingPage(animationResId = pages[page])
        }

Box(Modifier.weight(0.2f)) {
    Text(
        "Plan trip to more than 90 countries",
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
            Button(onClick = {
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

@Composable
fun OnboardingPage(animationResId: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationResId))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Gray.copy(alpha = 0.2f) )
            .fillMaxWidth().fillMaxSize()
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier.fillMaxSize()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChadventMPCSTheme {
        OnboardingScreen({}, PaddingValues(0.dp))
    }
}