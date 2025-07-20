package com.emperormoh.androidengrassignment.presentation

import androidx.compose.ui.graphics.vector.ImageVector

data class Shipment(
    val id: String,
    val status: ShipmentStatus,
    val price: String,
    val date: String
)

enum class ShipmentStatus {
    InProgress, Pending, Loading, CanCelled
}

data class VehicleData(
    val title: String,
    val subtitle: String,
    val icon: ImageVector
)

data class BottomNavItemData(
    val title: String,
    val icon: ImageVector
)


data class TabItemData(
    val title: String,
    val count: Int
)

data class ShipmentSearch(
    val title: String,
    val trackingNumber: String,
    val from: String,
    val to: String
)