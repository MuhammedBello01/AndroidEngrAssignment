package com.emperormoh.androidengrassignment.presentation.screens

import DatePickerDemoRy
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emperormoh.androidengrassignment.components.CustomDatePickerBottomSheet
import com.emperormoh.androidengrassignment.components.ExpandableText
import com.emperormoh.androidengrassignment.components.ExpandableText2
import com.emperormoh.androidengrassignment.components.PreviewCustomDatePickerBottomSheet
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen( onBackClick: () -> Unit){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically),
                title = {
                    Text(
                        "Profile Screen",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 10.dp).clickable{ onBackClick()},
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6A1B9A))
            )
        }
    ){ paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
            contentAlignment = Alignment.Center
        ){
            //Text("Profile Screen", fontWeight = FontWeight.Bold, fontSize = 20.sp)
//            ExpandableText2(
//                text = "This is a very long message that should show see more if it is more than 50 characters, and collapse back when see less is clicked. " +
//                        "jdshfdhkjhdwkshjsdjfhkjhsalkjhjkksa kjdshfjkdshjkhdsjghjksdahflkadsj jkfhwjdkhgkjdshafkjlhasdjkfhkjhfjsdjhfsdjkhfjksdhkjflds"
//            )

            //PreviewCustomDatePickerBottomSheet()
//            CustomDatePickerBottomSheet(
//                initialDate = LocalDate.of(2025, 4, 25), // Example selected date
//                onDismiss = { /* preview dismiss */ },
//                onConfirm = { selectedDate ->
//                    println("Selected date: $selectedDate") // Logs in preview
//                }
//            )

            DatePickerDemoRy()


        }
    }

}