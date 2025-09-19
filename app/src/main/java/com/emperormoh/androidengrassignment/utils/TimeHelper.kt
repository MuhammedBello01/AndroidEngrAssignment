package com.emperormoh.androidengrassignment.utils

import java.time.*

fun marketCountdown(
    openHour: Int = 9,
    openMinute: Int = 0,
    closeHour: Int,
    closeMinute: Int,
    now: ZonedDateTime = ZonedDateTime.now(ZoneId.of("Africa/Lagos"))
): String {
    val zone = ZoneId.of("Africa/Lagos")
    val today = now.toLocalDate()

    val openTime = ZonedDateTime.of(today, LocalTime.of(openHour, openMinute), zone)

    // If close is past midnight → assign to next day
    val closeTime = if (closeHour < openHour || (closeHour == openHour && closeMinute < openMinute)) {
        ZonedDateTime.of(today.plusDays(1), LocalTime.of(closeHour, closeMinute), zone)
    } else {
        ZonedDateTime.of(today, LocalTime.of(closeHour, closeMinute), zone)
    }

    val (label, duration) = when {
        now.isBefore(openTime) -> "Opens in" to Duration.between(now, openTime)
        now.isBefore(closeTime) -> "Closes in" to Duration.between(now, closeTime)
        else -> {
            val nextOpen = openTime.plusDays(1)
            "Opens in" to Duration.between(now, nextOpen)
        }
    }

    val hours = duration.toHours()
    val minutes = (duration.toMinutes() % 60) // manual "minutesPart"

    return "$label ${hours}hr ${minutes}m"
}

fun isMarketOpen(
    openHour: Int,
    openMinute: Int,
    closeHour: Int,
    closeMinute: Int,
    now: ZonedDateTime = ZonedDateTime.now(ZoneId.of("Africa/Lagos"))
): Boolean {
    val zone = ZoneId.of("Africa/Lagos")
    val today = now.toLocalDate()
    val openTime = ZonedDateTime.of(today, LocalTime.of(openHour, openMinute), zone)
    val closeTime = if (closeHour < openHour || (closeHour == openHour && closeMinute < openMinute)) {
        ZonedDateTime.of(today.plusDays(1), LocalTime.of(closeHour, closeMinute), zone)
    } else {
        ZonedDateTime.of(today, LocalTime.of(closeHour, closeMinute), zone)
    }
    return now.isAfter(openTime) && now.isBefore(closeTime)
}

