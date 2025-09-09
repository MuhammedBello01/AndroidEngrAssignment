@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emperormoh.androidengrassignment.components.CustomDatePickerBottomSheet2
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun CustomDatePickerBottomSheet(
    selectedDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    var currentMonth by remember { mutableStateOf(YearMonth.from(selectedDate)) }
    var showYearDropdown by remember { mutableStateOf(false) }
    var selectedDay by remember { mutableIntStateOf(selectedDate.dayOfMonth) }

    val primaryRed = Color(0xFFB91C47)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Header with navigation
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        val newMonth = currentMonth.minusMonths(1)
                        currentMonth = newMonth
                        val maxDayInNewMonth = newMonth.lengthOfMonth()
                        if (selectedDay > maxDayInNewMonth) {
                            selectedDay = maxDayInNewMonth
                        }
                    }
                ) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous month", tint = Color.Gray)
                }

                Box {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { showYearDropdown = true }
                    ) {
                        Text(
                            text = "${currentMonth.month.name.lowercase().replaceFirstChar { it.uppercase() }} ${currentMonth.year}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Select year", tint = Color.Gray, modifier = Modifier.size(20.dp))
                    }

                    if (showYearDropdown) {
                        YearDropdown(
                            currentYear = currentMonth.year,
                            onYearSelected = { year ->
                                val newMonth = currentMonth.withYear(year)
                                currentMonth = newMonth
                                val maxDayInNewMonth = newMonth.lengthOfMonth()
                                if (selectedDay > maxDayInNewMonth) {
                                    selectedDay = maxDayInNewMonth
                                }
                                showYearDropdown = false
                            },
                            onDismiss = { showYearDropdown = false }
                        )
                    }
                }

                IconButton(
                    onClick = {
                        val newMonth = currentMonth.plusMonths(1)
                        currentMonth = newMonth
                        val maxDayInNewMonth = newMonth.lengthOfMonth()
                        if (selectedDay > maxDayInNewMonth) {
                            selectedDay = maxDayInNewMonth
                        }
                    }
                ) {
                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next month", tint = Color.Gray)
                }
            }

            Spacer(Modifier.height(20.dp))

            // Days of week header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").forEach { day ->
                    Text(
                        text = day,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Calendar grid
            CalendarGrid(
                currentMonth = currentMonth,
                selectedDay = selectedDay,
                primaryColor = primaryRed,
                onDaySelected = { day -> selectedDay = day }
            )

            Spacer(Modifier.height(24.dp))

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.textButtonColors(contentColor = primaryRed)
                ) {
                    Text("Cancel", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }

                Button(
                    onClick = {
                        val maxDay = currentMonth.lengthOfMonth()
                        val safeDay = selectedDay.coerceAtMost(maxDay)
                        val newDate = LocalDate.of(currentMonth.year, currentMonth.month, safeDay)
                        onDateSelected(newDate)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = primaryRed),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.width(80.dp)
                ) {
                    Text("Done", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun CalendarGrid(
    currentMonth: YearMonth,
    selectedDay: Int,
    primaryColor: Color,
    onDaySelected: (Int) -> Unit
) {
    val firstDayOfMonth = currentMonth.atDay(1)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value - 1 // Monday=0 … Sunday=6
    val daysInMonth = currentMonth.lengthOfMonth()
    val daysInPreviousMonth = currentMonth.minusMonths(1).lengthOfMonth()

    Column {
        var dayCounter = 1
        var nextMonthCounter = 1

        repeat(6) { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(7) { dayOfWeek ->
                    val dayIndex = week * 7 + dayOfWeek

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        when {
                            dayIndex < firstDayOfWeek -> {
                                val day = daysInPreviousMonth - firstDayOfWeek + dayIndex + 1
                                Text(text = day.toString(), fontSize = 16.sp, color = Color.LightGray)
                            }
                            dayCounter <= daysInMonth -> {
                                val isSelected = dayCounter == selectedDay
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                        .background(if (isSelected) primaryColor else Color.Transparent)
                                        .clickable { onDaySelected(dayCounter) },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = dayCounter.toString(),
                                        fontSize = 16.sp,
                                        color = if (isSelected) Color.White else Color.Black,
                                        fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                                    )
                                }
                                dayCounter++
                            }
                            else -> {
                                Text(text = nextMonthCounter.toString(), fontSize = 16.sp, color = Color.LightGray)
                                nextMonthCounter++
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(4.dp))
        }
    }
}

@Composable
fun YearDropdown(
    currentYear: Int,
    onYearSelected: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        expanded = true,
        onDismissRequest = onDismiss,
        modifier = Modifier
            .height(200.dp) // keeps dropdown usable
    ) {
        val years = (currentYear - 10..currentYear + 10).toList()
        years.forEach { year ->
            DropdownMenuItem(
                text = {
                    Text(year.toString(), color = if (year == currentYear) Color(0xFFB91C47) else Color.Black)
                },
                onClick = { onYearSelected(year) }
            )
        }
    }
}

@Composable
fun DatePickerDemoRy() {
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateQ by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { showDatePicker = true }) {
            Text("Show Date Picker")
        }

        Spacer(Modifier.height(16.dp))

        Text("Selected Date: ${selectedDateQ.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))}")

        if (showDatePicker) {
//            CustomDatePickerBottomSheet(
//                selectedDate = selectedDate,
//                onDateSelected = { date ->
//                    selectedDate = date
//                    showDatePicker = false
//                    Log.e("TAG", "DatePickerDemoRy: ${selectedDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))}", )
//                },
//                onDismiss = { showDatePicker = false }
//            )
            CustomDatePickerBottomSheet2(
                initialDate = LocalDate.now(), // Example selected date
                onDismiss = { showDatePicker = false},
                minDate = LocalDate.of(2025, 4, 25),
                //maxDate = LocalDate.of(2026, 1, 10),
                onConfirm = { selectedDate ->
                    selectedDateQ = selectedDate
                    showDatePicker = false
                    println("Selected date: $selectedDate") // Logs in preview
                }
            )
        }
    }
}
