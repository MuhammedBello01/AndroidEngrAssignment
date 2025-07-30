package com.emperormoh.androidengrassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emperormoh.androidengrassignment.presentation.screens.CalculateScreen
import com.emperormoh.androidengrassignment.presentation.screens.EstimateScreen
import com.emperormoh.androidengrassignment.presentation.screens.HomeScreen
import com.emperormoh.androidengrassignment.presentation.screens.ProfileScreen
import com.emperormoh.androidengrassignment.presentation.Route
import com.emperormoh.androidengrassignment.presentation.screens.ShipmentScreen
import com.emperormoh.androidengrassignment.ui.theme.AndroidEngrAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidEngrAssignmentTheme {
               Surface( modifier = Modifier.fillMaxSize()) {
                   AppNavHost()
                   DraggableFloatingIcon()
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
    ) {
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

@Composable
fun DraggableFloatingIcon() {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.BugReport,
            tint = Color.Red,
            contentDescription = "Draggable Icon",
            modifier = Modifier
                .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }
                .size(50.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        )
    }
}
