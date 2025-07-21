package com.emperormoh.androidengrassignment.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emperormoh.androidengrassignment.components.AvailableVehiclesSection
import com.emperormoh.androidengrassignment.components.BottomNavigationBar
import com.emperormoh.androidengrassignment.components.ShipmentSearchItem
import com.emperormoh.androidengrassignment.components.TopHeader
import com.emperormoh.androidengrassignment.components.TrackingSection
import com.emperormoh.androidengrassignment.models.BottomNavItemData
import com.emperormoh.androidengrassignment.presentation.Route
import com.emperormoh.androidengrassignment.data.sampleShipmentsSearch
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
            // Top Header
            TopHeader(
                searchText = searchText,
                onSearchTextChange = { searchText = it},
                onClearSearchClick = { searchText = "" }
            )

            // Main Content
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
                    AvailableVehiclesSection()
                    Spacer(modifier = Modifier.height(200.dp)) // Space for bottom nav
                }
            }

        }

        //Bottom Nav
        BottomNavigationBar(
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


fun navigateToTan(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop =
                true
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