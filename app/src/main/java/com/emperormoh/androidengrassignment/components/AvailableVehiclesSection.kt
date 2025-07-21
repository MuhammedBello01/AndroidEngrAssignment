package com.emperormoh.androidengrassignment.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emperormoh.androidengrassignment.data.vehicleDataItems

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AvailableVehiclesSection() {
    Column {
        Text(
            text = "Available vehicles",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(vehicleDataItems) { item ->
                VehicleCard(
                    title = item.title,
                    subtitle = item.subtitle,
                    icon = item.icon
                )
            }
        }
    }
}