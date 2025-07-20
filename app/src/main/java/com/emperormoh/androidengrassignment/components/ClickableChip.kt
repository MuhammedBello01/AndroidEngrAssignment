package com.emperormoh.androidengrassignment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClickableChip(
    modifier: Modifier = Modifier,
    value: String,
    isSelected: Boolean = false,
    onClick: (String) -> Unit
){
    val backgroundColor = if (isSelected) Color.Black else Color.White
    val borderColor = Color.Gray
    val textColor = if (isSelected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .padding(vertical = 5.dp)
            //.wrapContentSize()
            .sizeIn(minWidth = 60.dp, minHeight = 40.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(6.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(6.dp),
                color = borderColor
            )
            .clickable { onClick(value) },
        contentAlignment = Alignment.Center
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelected){
                Spacer(Modifier.width(10.dp))
                Icon(
                    modifier = modifier.size(18.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = "Dropdown",
                    tint = Color.White
                )
            }
            Text(
                text = value,
                modifier = modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}