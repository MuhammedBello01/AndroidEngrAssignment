package com.emperormoh.androidengrassignment.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBoat
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.LocalShipping
import com.emperormoh.androidengrassignment.models.Shipment
import com.emperormoh.androidengrassignment.models.ShipmentSearchData
import com.emperormoh.androidengrassignment.models.ShipmentStatus
import com.emperormoh.androidengrassignment.models.VehicleData

val vehicleDataItems = listOf(
    VehicleData(
        title = "Ocean freight",
        subtitle = "International",
        icon = Icons.Default.DirectionsBoat
    ),
    VehicleData(title =  "Cargo freight", subtitle = "Reliable", icon = Icons.Default.LocalShipping),
    VehicleData(title =  "Air freight", subtitle = "International", icon = Icons.Default.Flight),
)

val sampleShipments = listOf(
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$1400", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Pending, "$650", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Pending, "$650", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Loading, "$230", "Jan 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$1400", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$370", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$3570", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$1400", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Pending, "$650", "Oct 20,2025"),
    Shipment("NEJ20089934122231", ShipmentStatus.Pending, "$650", "Nov 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Loading, "$230", "Feb 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$1400", "Oct 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$370", "Nov 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$3570", "Dec 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Loading, "$230", "Mar 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Loading, "$230", "April 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$1400", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Pending, "$650", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Pending, "$650", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Loading, "$230", "Jan 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$1400", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$370", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$3570", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$1400", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Pending, "$650", "Oct 20,2025"),
    Shipment("NEJ20089934122231", ShipmentStatus.Pending, "$650", "Nov 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Loading, "$230", "Feb 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$1400", "Oct 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$370", "Nov 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$3570", "Dec 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Loading, "$230", "Mar 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.Loading, "$230", "April 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.InProgress, "$1400", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$370", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$3570", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$1400", "Sep 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$650", "Oct 20,2025"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$650", "Nov 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$230", "Feb 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$1400", "Oct 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$370", "Nov 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$3570", "Dec 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$230", "Mar 20,2023"),
    Shipment("NEJ20089934122231", ShipmentStatus.CanCelled, "$230", "April 20,2023"),
)

val sampleShipmentsSearch = listOf(
    ShipmentSearchData("Macbook pro M2", "#NE43857340857904", "Paris", "Morocco"),
    ShipmentSearchData("Summer linen jacket", "#NEJ20089934122231", "Barcelona", "Paris"),
    ShipmentSearchData("Tapered-fit jeans AW", "#NEJ35870264978659", "Colombia", "Paris"),
    ShipmentSearchData("Slim fit jeans AW", "#NEJ35870264978659", "Bogota", "Dhaka"),
    ShipmentSearchData("Office setup desk", "#NEJ23481570754963", "France", "German")
)

