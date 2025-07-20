package com.emperormoh.androidengrassignment.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.HistoryToggleOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emperormoh.androidengrassignment.R
import com.emperormoh.androidengrassignment.models.Shipment
import com.emperormoh.androidengrassignment.models.ShipmentStatus
import com.emperormoh.androidengrassignment.models.TabItemData
import com.emperormoh.androidengrassignment.presentation.sampleShipments
import com.emperormoh.androidengrassignment.ui.theme.AndroidEngrAssignmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentScreen(onBackClick: () -> Unit){
    val theTabItems = listOf(
        TabItemData(title = "All", count = 12),
        TabItemData(title = "Completed", count = 5),
        TabItemData(title = "In Progress", count = 3),
        TabItemData(title = "Pending Order", count = 4),
        TabItemData(title = "Cancelled", count = 1)
    )
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { theTabItems.size }
    LaunchedEffect(selectedTabIndex) { pagerState.animateScrollToPage(selectedTabIndex) }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column(modifier = Modifier.fillMaxSize()
    ){
        Column( modifier = Modifier.background(Color(0xFF6A1B9A))) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Shipment history",
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
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF6A1B9A))
            )

            ScrollableTabRow(
                modifier = Modifier,
                containerColor = Color(0xFF6A1B9A),
                edgePadding = 10.dp,
                selectedTabIndex = selectedTabIndex,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Color(0xFFFF9800) // Orange
                    )
                }
            ){
                theTabItems.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index },
                        text = {
                            TabItem(
                                label = item.title,
                                count = item.count,
                                isSelected = index == selectedTabIndex
                            )
                        }
                    )
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ){ page ->
            when (page){
                0 -> AllShipmentsScreen(sampleShipments)
//                1 -> CompletedShipmentsScreen()
                2 -> AllShipmentsScreen(sampleShipments.filter { it.status == ShipmentStatus.InProgress })
                3 -> AllShipmentsScreen(sampleShipments.filter { it.status == ShipmentStatus.Pending })
                4 -> AllShipmentsScreen(sampleShipments.filter { it.status == ShipmentStatus.CanCelled })
            }

        }
    }
}

@Composable
fun TabItem(
    label: String,
    count: Int,
    isSelected: Boolean = false
) {
    Row(
        modifier = Modifier.padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = if (!isSelected) FontWeight.Normal else FontWeight.Bold,
            color = Color.White,
        )
        Spacer(modifier = Modifier.width(8.dp))
        if (count > 0){
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(24.dp)
                    .background(
                        color = if (isSelected) Color(0xFFFF9800) else Color(0xFFE3C5F0).copy(alpha = 0.3f),
                        shape = RoundedCornerShape(40)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = count.toString(),
                    fontSize = 10.sp,
                    fontWeight = if (!isSelected) FontWeight.Medium else FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun StatusChip(status: ShipmentStatus) {
    val (text, color, icon) = when (status) {
        ShipmentStatus.InProgress -> Triple("in-progress", Color(0xFF4CAF50), Icons.Default.Autorenew)
        ShipmentStatus.Pending -> Triple("pending", Color(0xFFFF9800), Icons.Default.History)
        ShipmentStatus.Loading -> Triple("loading", Color(0xFF2196F3), Icons.Default.HistoryToggleOff)
        ShipmentStatus.CanCelled -> Triple("Cancelled", Color(0xFFE57373), Icons.Default.Cancel)
    }
    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.1f), shape = CircleShape)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Sender",
                tint = color,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = text, color = color, fontSize = 12.sp)
        }
    }
}
@Composable
fun ShipmentCard(shipment: Shipment) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                StatusChip(shipment.status)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Arriving today!", fontWeight = FontWeight.Bold)
                Text(
                    text = "Your delivery, #${shipment.id} from Atlanta, is arriving today!",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        text = "${shipment.price} USD • ",
                        fontSize = 14.sp,
                        color = Color(0xFF6A1B9A),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " ${shipment.date}",
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    )
                }

            }
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_box),
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShipmentScreenPreview() {
    AndroidEngrAssignmentTheme {
        ShipmentScreen(onBackClick = {})
    }
}