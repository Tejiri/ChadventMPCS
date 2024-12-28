package com.xita.chadventmpcs.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xita.chadventmpcs.R
import com.xita.chadventmpcs.viewModels.ChadventDatabaseViewModel

@Composable
fun ContactsPage(
    innerPadding: PaddingValues,
    chadventDatabaseViewModel: ChadventDatabaseViewModel
) {
    val members by chadventDatabaseViewModel.allMembers.collectAsState(initial = emptyList())
//    members.forEach{ member->
//        Log.i("MYINFO",member.toString())
//    }
    LazyColumn(
        Modifier
            .padding(innerPadding)
            .background(Color.Blue.copy(0.2f))
            .padding(5.dp)
            .fillMaxSize()
    ) {
        items(members) { member ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .background(Color.White.copy(alpha = 0.9f))
                    .border(1.dp, Color.Black)
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Box(
//                    Modifier
//                        .clip(RoundedCornerShape(100))
//                        .background(Color.Blue)
//                        .padding(8.dp)
//                        .width(30.dp)
//                        .height(30.dp)
//                ) {

                Image(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),

                    painter = painterResource(if (member.gender == "Male") R.drawable.man else R.drawable.woman),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,

                    )
//                }

                Column(
                    Modifier
                        .weight(1f)
                        .padding(start = 7.dp)
                ) {
                    Text(
                        "${member.firstname + " " + member.lastname}",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    )
                    Text(member.phonenumber)

                }

                Icon(Icons.Default.Phone, contentDescription = "")
            }
        }
//        items(20) {
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 5.dp)
//                    .background(Color.White.copy(alpha = 0.9f))
//                    .border(1.dp, Color.Black)
//                    .padding(15.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
////                Box(
////                    Modifier
////                        .clip(RoundedCornerShape(100))
////                        .background(Color.Blue)
////                        .padding(8.dp)
////                        .width(30.dp)
////                        .height(30.dp)
////                ) {
//
//                Image(
//                    modifier = Modifier
//                        .width(50.dp)
//                        .height(50.dp),
//
//                    painter = painterResource(R.drawable.man),
//                    contentDescription = "",
//                    contentScale = ContentScale.FillBounds,
//
//                    )
////                }
//
//                Column(
//                    Modifier
//                        .weight(1f)
//                        .padding(start = 7.dp)
//                ) {
//                    Text(
//                        "Stephen Ijatomi",
//                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp)
//                    )
//                    Text("09013458819")
//
//                }
//
//                Icon(Icons.Default.Phone, contentDescription = "")
//            }
//
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 5.dp)
//                    .background(Color.White.copy(alpha = 0.9f))
//                    .border(1.dp, Color.Black)
//                    .padding(15.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
////                Box(
////                    Modifier
////                        .clip(RoundedCornerShape(100))
////                        .background(Color.Blue)
////                        .padding(8.dp)
////                        .width(30.dp)
////                        .height(30.dp)
////                ) {
//
//                Image(
//                    modifier = Modifier
//                        .width(50.dp)
//                        .height(50.dp),
//
//                    painter = painterResource(R.drawable.woman),
//                    contentDescription = "",
//                    contentScale = ContentScale.FillBounds,
//
//                    )
////                }
//
//                Column(
//                    Modifier
//                        .weight(1f)
//                        .padding(start = 7.dp)
//                ) {
//                    Text(
//                        "Stephen Ijatomi",
//                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp)
//                    )
//                    Text("09013458819")
//
//                }
//
//                Icon(Icons.Default.Phone, contentDescription = "")
//            }
//
//        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewContactsPage() {
    ContactsPage(PaddingValues(0.dp), chadventDatabaseViewModel = viewModel())
}