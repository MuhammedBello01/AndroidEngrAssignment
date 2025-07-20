package com.emperormoh.androidengrassignment.models

data class Shipment(
    val id: String,
    val status: ShipmentStatus,
    val price: String,
    val date: String
)