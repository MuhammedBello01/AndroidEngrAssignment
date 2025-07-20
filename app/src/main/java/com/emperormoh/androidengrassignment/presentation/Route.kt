package com.emperormoh.androidengrassignment.presentation



sealed class Route(val route : String) {
    object HomeScreen : Route(route = "homeScreen")

    object CalculateScreen : Route(route = "calculateScreen")

    object ShipmentScreen : Route(route = "shipmentScreen")

    object ProfileScreen : Route(route = "profileScreen")

    object EstimateScreen : Route(route = "estimateScreen")

}