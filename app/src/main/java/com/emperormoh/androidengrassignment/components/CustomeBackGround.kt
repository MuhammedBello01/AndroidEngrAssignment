package com.emperormoh.androidengrassignment.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill

@Composable
fun CustomBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        // Base white background
        drawRect(
            color = Color.White,
            size = size
        )

        // Ellipse 1 (gray tone)
        drawOval(
            color = Color(0xFFAEB3B6),
            topLeft = Offset(-width * 0.02f, 0f),
            size = Size(width * 1.05f, height * 2f)
        )

        // Ellipse 2 (slightly brown)
        drawOval(
            color = Color(0xFFC0B3AD),
            topLeft = Offset(width * 0.48f, height),
            size = Size(width * 1.05f, height * 2f)
        )

        // Ellipse 3 (light gray)
        drawOval(
            color = Color(0xFFC8C4C1),
            topLeft = Offset(-width * 0.5f, height),
            size = Size(width * 1.05f, height * 2f)
        )

        // Ellipse 4 (darker gray)
        drawOval(
            color = Color(0xFFAEAAA7),
            topLeft = Offset(width * 0.48f, -height * 0.9f),
            size = Size(width * 1.05f, height * 2f)
        )

        // Gradient overlay (same as <gradient> in your XML)
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFB2214A),
                    Color(0xFF6B214A)
                ),
                start = Offset(0f, height * 0.2f),
                end = Offset(width * 0.85f, height * 0.9f)
            ),
            size = size
        )
    }
}
