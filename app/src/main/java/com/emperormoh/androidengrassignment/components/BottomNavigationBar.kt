package com.emperormoh.androidengrassignment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emperormoh.androidengrassignment.models.BottomNavItemData

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavItemData>,
    selected: Int,
    onItemClicked: (Int) -> Unit,
){
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ){
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
            tonalElevation = 10.dp
        ){
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = false,
                    onClick = { onItemClicked(index) },
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (index == selected) {
                                Box(
                                    modifier = Modifier
                                        .height(3.dp)
                                        .width(50.dp)
                                        .background(
                                            Color(0xFF7C4DFF),
                                            shape = RoundedCornerShape(1.5.dp)
                                        )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = if (index == selected) Color(0xFF7C4DFF) else Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.title,
                                fontSize = 12.sp,
                                color = if (index == selected) Color(0xFF7C4DFF) else Color.Gray
                            )
                        }
                    }
                )
            }
        }
    }
}