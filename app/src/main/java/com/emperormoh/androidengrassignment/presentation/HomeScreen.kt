package com.emperormoh.androidengrassignment.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emperormoh.androidengrassignment.R
import com.emperormoh.androidengrassignment.ui.theme.AndroidEngrAssignmentTheme

@Composable
fun HomeScreen(navController: NavController){
    var searchText by remember { mutableStateOf("") }
    //val navController = rememberNavController()
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val backstackState = navController.currentBackStackEntryAsState().value
    selectedItem = remember(key1 = backstackState){
        when (backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.CalculateScreen.route -> 1
            Route.ShipmentScreen.route -> 2
            Route.ProfileScreen.route -> 3
            else -> 0
        }
    }
    val bottomNavigationItems = remember{
        listOf(
            BottomNavItemData(icon = Icons.Default.Home, title = "Home"),
            BottomNavItemData(icon = Icons.Default.Calculate, title = "Calculate"),
            BottomNavItemData(icon = Icons.Default.History, title = "Shipment"),
            BottomNavItemData(icon = Icons.Default.Person, title = "Profile"),
        )
    }
//    val isBottomBarVisible = remember(key1 = backstackState){
//        backstackState?.destination?.route == Route.HomeScreen.route
//                || backstackState?.destination?.route == Route.CalculateScreen.route ||
//                backstackState?.destination?.route == Route.ShipmentScreen.route ||
//                backstackState?.destination?.route == Route.ProfileScreen.route
//    }
    Box( modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F8))
        ){
            // Top Header with gradient background
            TopHeader(
                searchText = searchText,
                onSearchTextChange = { searchText = it},
                onClearSearchClick = { searchText = "" }
            )

            if (searchText.isNotBlank()){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF7F7F8))
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(12.dp)
                    ) {
                        items(sampleShipmentsSearch) { shipment ->
                            ShipmentSearchItem(shipment)
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp),
                                color = Color.Gray.copy(alpha = 0.2f)
                            )
                        }
                    }
                }

            }else{
                // Main Content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    // Tracking Section
                    TrackingSection()
                    Spacer(modifier = Modifier.height(32.dp))
                    // Available Vehicles Section
                    AvailableVehiclesSection2()
                    Spacer(modifier = Modifier.height(200.dp)) // Space for bottom nav
                }
            }

        }
        BottomNavBar(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            items = bottomNavigationItems,
            selected = selectedItem,
            onItemClicked = { index ->
                when (index) {
                    0 -> navigateToTan(
                        navController = navController,
                        route = Route.HomeScreen.route
                    )

                    1 -> navigateToTan(
                        navController = navController,
                        route = Route.CalculateScreen.route
                    )

                    2 -> navigateToTan(
                        navController = navController,
                        route = Route.ShipmentScreen.route
                    )

                    3 -> navigateToTan(
                        navController = navController,
                        route = Route.ProfileScreen.route
                    )
                }
            }
        )
    }
}

@Composable
fun TrackingSection() {
    Column {
        Text(
            text = "Tracking",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Tracking Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                // Shipment number row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Shipment Number",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "NEJ2008993412231",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    // Package icon
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color(0xFFFFF3E0), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalShipping,
                            contentDescription = "Package",
                            tint = Color(0xFFFF8A65),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 20.dp),
                    color = Color.Gray.copy(alpha = 0.2f),
                    thickness = 1.dp
                )

                // Sender and Time row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    //horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Sender column
                    Column(
                        modifier = Modifier.weight(6f),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFFE0B2)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowUpward,
                                    contentDescription = "Sender",
                                    tint = Color(0xFFFF8A65),
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = "Sender",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Atlanta, 5243",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                        }
                    }

                    // Time column
                    Column(
                        modifier = Modifier.weight(4f)
                    ) {
                        Text(
                            text = "Time",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF4CAF50))
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "2 day -3 days",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Receiver and Status row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.weight(6f)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFE8F5E8)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowDownward,
                                    contentDescription = "Receiver",
                                    tint = Color(0xFF4CAF50),
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = "Receiver",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Chicago, 6342",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                        }
                    }

                    // Status column
                    Column(
                        modifier = Modifier.weight(4f),
                    ) {
                        Text(
                            text = "Status",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Waiting to collect",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                    color = Color.Gray.copy(alpha = 0.2f),
                    thickness = 1.dp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color(0xFFFF8A65),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Add Stop",
                        color = Color(0xFFFF8A65),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AvailableVehiclesSection2() {
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
                VehicleCard2(
                    title = item.title,
                    subtitle = item.subtitle,
                    icon = item.icon
                )
            }
        }
    }
}

@Composable
fun VehicleCard2(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: ImageVector,
) {
    Box(
        modifier = modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(14.dp), clip = false)
            .background(color = Color.White, shape = RoundedCornerShape(14.dp))
    ) {
        Column {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )
            }
        }
    }
}

@Composable
fun BottomNavBar(
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

@Composable
fun TopHeader(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onClearSearchClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFF6A1B9A))
    ) {
        Column {
            // Profile and location section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Profile image
                    Image(
                        painter = painterResource(id = R.drawable.placeholder_profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location",
                                tint = Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Your location",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 16.sp
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Wertheimer, Illinois",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Dropdown",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                // Notification icon
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.NotificationsNone,
                        contentDescription = "Notifications",
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar(
                searchText = searchText,
                onSearchTextChange = onSearchTextChange,
                onClearSearchClick = onClearSearchClick
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onClearSearchClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (searchText.isNotBlank()){
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable { onClearSearchClick() }
                    .size(30.dp),
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = if (searchText.isNotBlank()) 10.dp else 20.dp, end = 20.dp)
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                placeholder = {
                    Text(
                        text = "Enter the receipt number ...",
                        color = Color.Gray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    Box(
                        modifier = modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFF8A65)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(25.dp)
            )
        }
    }
}

@Composable
fun ShipmentSearchItem(shipment: ShipmentSearch) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF6A1B9A), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Inventory2,
                contentDescription = "Package",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(text = shipment.title, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Text(
                text = "${shipment.trackingNumber} • ${shipment.from} → ${shipment.to}",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }
    }
}

fun navigateToTan(navController: NavController, route: String) {
    navController.navigate(route) {
        // every time we navigate to tab we wanna pop the backstack until we reach the home screen
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop =
                true // if you clicked multiple time on home screen icon that won't create a new instance of home screen each time
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AndroidEngrAssignmentTheme {
        val navController = rememberNavController()
        HomeScreen(
            navController = navController
        )
    }
}