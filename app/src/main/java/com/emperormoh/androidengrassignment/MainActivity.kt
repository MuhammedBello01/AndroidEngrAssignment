package com.emperormoh.androidengrassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emperormoh.androidengrassignment.presentation.CalculateScreen
import com.emperormoh.androidengrassignment.presentation.EstimateScreen
import com.emperormoh.androidengrassignment.presentation.HomeScreen
import com.emperormoh.androidengrassignment.presentation.ProfileScreen
import com.emperormoh.androidengrassignment.presentation.Route
import com.emperormoh.androidengrassignment.presentation.ShipmentScreen
import com.emperormoh.androidengrassignment.ui.theme.AndroidEngrAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidEngrAssignmentTheme {
               Surface( modifier = Modifier.fillMaxSize()) {
                   AppNavHost()
               }
            }
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen.route,
    ){
        composable(Route.HomeScreen.route){
            HomeScreen(navController)
        }

        composable(Route.ShipmentScreen.route){
            ShipmentScreen(onBackClick = navController::popBackStack)
        }

        composable(Route.CalculateScreen.route){
            CalculateScreen(
                onBackClick = navController::popBackStack,
                onNavigate = { navController.navigate(Route.EstimateScreen.route) }
            )
        }

        composable(Route.EstimateScreen.route){
            EstimateScreen(onBackToHomeClick = { navController.navigate(Route.HomeScreen.route) })
        }

        composable(Route.ProfileScreen.route){
            ProfileScreen(onBackClick = navController::popBackStack)
        }
    }
}