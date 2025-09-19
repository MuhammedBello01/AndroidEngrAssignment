package com.emperormoh.androidengrassignment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emperormoh.androidengrassignment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FxSalesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FX Sales") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Back action */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Market Info Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD32F6C)) // pink-ish background
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Market is Open in 5hr 26m",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "For You",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(12.dp))

            // Grid of currencies
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(currencyList) { currency ->
                    CurrencyCard(currency)
                }
            }
        }
    }
}

data class Currency(
    val code: String,
    val rate: String,
    val flagRes: Int
)

val currencyList = listOf(
    Currency("USD", "$1 = ₦1,600", R.drawable.core_ui_usd_flag),
    Currency("GBP", "£1 = ₦1,600", R.drawable.core_ui_gbp_flag),
    Currency("EUR", "€1 = ₦1,600", R.drawable.core_ui_eur_flag),
    Currency("CAD", "C$1 = ₦1,600", R.drawable.core_ui_cad_flag),
)

@Composable
fun CurrencyCard(currency: Currency) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = currency.flagRes),
                contentDescription = "${currency.code} flag",
                modifier = Modifier.size(36.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = currency.code,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = currency.rate,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FxSalesScreenPreview() {
    FxSalesScreenPreviewContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FxSalesScreenPreviewContent() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FX Sales") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { padding ->
        //CustomBackground()
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Market Info Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD32F6C))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Market is Open in 5hr 26m",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "For You",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(12.dp))

            // Grid of currencies
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(currencyList) { currency ->
                    CurrencyCardPreview(currency)
                }
            }
        }
    }
}

@Composable
fun CurrencyCardPreview(currency: Currency) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Flag,
                contentDescription = "${currency.code} flag",
                tint = Color(0xFF1976D2),
                modifier = Modifier.size(36.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = currency.code,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = currency.rate,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
        }
    }
}

