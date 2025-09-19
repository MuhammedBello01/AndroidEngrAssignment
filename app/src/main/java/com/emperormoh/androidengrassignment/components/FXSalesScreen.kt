package com.emperormoh.androidengrassignment.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CurrencyItem(
    val code: String,
    val rate: String,
    val flagResource: Int,
    val isSelected: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FXSalesScreen() {
    val currencies = listOf(
        CurrencyItem("USD", "₦1,600", android.R.drawable.ic_menu_gallery),
        CurrencyItem("GBP", "₦1,600", android.R.drawable.ic_menu_gallery),
        CurrencyItem("EUR", "₦1,600", android.R.drawable.ic_menu_gallery),
        CurrencyItem("CAD", "₦1,600", android.R.drawable.ic_menu_gallery, isSelected = true),
        CurrencyItem("JPY", "₦1,200", android.R.drawable.ic_menu_gallery) // Added 5th item for testing
    )

    val shinyGold = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFD700), // Gold
            Color(0xFFFFE55C), // Light gold
            Color(0xFFFFC107), // Rich gold
            Color(0xFFFFD700)  // Back to gold for shine
        )
    )

    val skyBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF87CEEB), // SkyBlue
            Color(0xFF00BFFF), // DeepSkyBlue
            Color(0xFF1E90FF), // DodgerBlue
            Color(0xFF87CEFA)  // LightSkyBlue
        )
    )

    val oceanBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFB3E5FC), // Light Sky Blue
            Color(0xFF81D4FA), // Soft Blue
            Color(0xFF4FC3F7), // Ocean Blue
            Color(0xFF29B6F6), // Bright Aqua Blue
            Color(0xFFB3E5FC)  // Back to Light Sky Blue
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top Bar
            TopAppBar(
                title = {
                    Text(
                        text = "FX Sales",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle headphones action */ }) {
                        Icon(
                            imageVector = Icons.Filled.DarkMode,
                            contentDescription = "Support",
                            modifier = Modifier
                                .size(32.dp) // adjust as needed
                                .graphicsLayer(alpha = 0.99f) // required to enable layer rendering
                                .drawWithCache {
                                    onDrawWithContent {
                                        drawContent()
                                        drawRect(oceanBrush, blendMode = BlendMode.SrcAtop)
                                    }
                                },
                            tint = Color.Unspecified
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFB91C5C)
                )
            )

            // Market Status
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFB91C5C))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Green, CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Market Is Open",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Closes In 5hr 26m",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
            }

            // For You Section
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "For You",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Currency Grid
                CurrencyGrid(currencies = currencies)
            }
        }
    }
}

@Composable
fun CurrencyGrid(currencies: List<CurrencyItem>) {
    val isOdd = currencies.size % 2 != 0
    val pairCount = currencies.size / 2

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Create rows with pairs of cards
        for (i in 0 until pairCount) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CurrencyCard(
                    currency = currencies[i * 2],
                    modifier = Modifier.weight(1f)
                )
                CurrencyCard(
                    currency = currencies[i * 2 + 1],
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // If odd number, add the last card taking full width
        if (isOdd) {
            CurrencyCard(
                currency = currencies.last(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CurrencyCard(currency: CurrencyItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (currency.isSelected) Color(0xFFE3F2FD) else Color.White
        ),
        border = if (currency.isSelected)
            androidx.compose.foundation.BorderStroke(2.dp, Color(0xFF2196F3))
        else null,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Flag placeholder (circle)
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(getFlagColor(currency.code)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = getFlagEmoji(currency.code),
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Currency Code
            Text(
                text = currency.code,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            // Exchange Rate
            Text(
                text = "₦1 = ${currency.rate}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun getFlagColor(currencyCode: String): Color {
    return when (currencyCode) {
        "USD" -> Color(0xFF1E3A8A)
        "GBP" -> Color(0xFFDC2626)
        "EUR" -> Color(0xFF1E40AF)
        "CAD" -> Color(0xFFDC2626)
        else -> Color.Gray
    }
}

fun getFlagEmoji(currencyCode: String): String {
    return when (currencyCode) {
        "USD" -> "🇺🇸"
        "GBP" -> "🇬🇧"
        "EUR" -> "🇪🇺"
        "CAD" -> "🇨🇦"
        else -> "🏳️"
    }
}

@Preview(showBackground = true)
@Composable
fun FXSalesScreenPreview() {
    MaterialTheme {
        FXSalesScreen()
    }
}