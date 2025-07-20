package com.emperormoh.androidengrassignment.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBoat
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.LocalShipping

val vehicleDataItems = listOf(
    VehicleData(title =  "Ocean freight", subtitle = "International", icon = Icons.Default.DirectionsBoat),
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