package com.emperormoh.androidengrassignment.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CustomDatePicker2(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
    onDone: () -> Unit
) {
    val currentMonth = remember { mutableStateOf(selectedDate.withDayOfMonth(1)) }
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun")

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    currentMonth.value = currentMonth.value.minusMonths(1)
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = currentMonth.value.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = currentMonth.value.year.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }

                IconButton(onClick = {
                    currentMonth.value = currentMonth.value.plusMonths(1)
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Days of week header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                daysOfWeek.forEach {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Days grid
            val firstDayOfWeek = currentMonth.value.dayOfWeek.value % 7 // Make Monday = 0
            val daysInMonth = currentMonth.value.lengthOfMonth()
            val today = LocalDate.now()

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier.height(300.dp)
            ) {
                // Empty cells before the first day
                items(firstDayOfWeek) { Spacer(modifier = Modifier.size(40.dp)) }

                // Days in current month
                items(daysInMonth) { day ->
                    val date = currentMonth.value.withDayOfMonth(day + 1)

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable { onDateSelected(date) }
                            .background(
                                when {
                                    date == selectedDate -> Color.Red.copy(alpha = 0.2f)
                                    else -> Color.Transparent
                                },
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (day + 1).toString(),
                            color = when {
                                date == selectedDate -> Color.Red
                                date.isBefore(today) -> Color.Gray
                                else -> Color.Black
                            },
                            fontWeight = if (date == selectedDate) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = onDismiss) {
                    Text("Cancel", color = Color.Red)
                }
                Button(
                    onClick = onDone,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Done", color = Color.White)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewCustomDatePickerBottomSheet() {
    var showSheet by remember { mutableStateOf(true) }
    var selectedDate by remember { mutableStateOf(LocalDate.of(2025, 4, 25)) }

    // Scaffold just to give background
    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            if (showSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                ) {
                    CustomDatePicker2(
                        selectedDate = selectedDate,
                        onDateSelected = { selectedDate = it },
                        onDismiss = { showSheet = false },
                        onDone = { showSheet = false }
                    )
                }
            }
        }
    }
}

@Composable
fun MonthHeader(
    currentMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Previous")
        }
        Text(
            text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        IconButton(onClick = onNextMonth) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Next")
        }
    }
}

@Composable
fun DaysOfWeekRow() {
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun")
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        daysOfWeek.forEach { day ->
            Text(
                text = day,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun DatesGrid(
    currentMonth: YearMonth,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    val firstDayOfMonth = currentMonth.atDay(1)
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7  // shift so Mon=0

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        // Empty slots for days before the 1st
        items(firstDayOfWeek) { Spacer(Modifier.size(40.dp)) }

        // Dates
        items(daysInMonth) { day ->
            val date = currentMonth.atDay(day + 1)
            val isSelected = date == selectedDate

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) Color.Red else Color.Transparent)
                    .clickable { onDateSelected(date) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (day + 1).toString(),
                    color = if (isSelected) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
fun DatePickerBottomActions(
    onDone: () -> Unit,
    onCancel: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onDone,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) { Text("Done") }

        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = onCancel
        ) { Text("Cancel") }
    }
}

//@Composable
//fun CustomDatePickerBottomSheet(
//    initialDate: LocalDate = LocalDate.now(),
//    onDismiss: () -> Unit,
//    onConfirm: (LocalDate) -> Unit
//) {
//    var selectedDate by remember { mutableStateOf<LocalDate?>(initialDate) }
//    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
//    val context = LocalContext.current
//
//    Column(modifier = Modifier.fillMaxWidth()) {
//        MonthHeader(
//            currentMonth = currentMonth,
//            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
//            onNextMonth = { currentMonth = currentMonth.plusMonths(1) }
//        )
//
//        DaysOfWeekRow()
//
//        DatesGrid(
//            currentMonth = currentMonth,
//            selectedDate = selectedDate,
//            onDateSelected = { selectedDate = it }
//        )
//
//        DatePickerBottomActions(
//            onDone = {
//                selectedDate?.let(onConfirm)
//                onDismiss()
//
//                Toast.makeText(context, "$selectedDate", Toast.LENGTH_LONG).show()
//            },
//            onCancel = onDismiss
//        )
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerBottomSheet(
    initialDate: LocalDate = LocalDate.now(),
    onDismiss: () -> Unit,
    onConfirm: (LocalDate) -> Unit
) {
    var selectedDate by remember { mutableStateOf(initialDate) }
    var currentMonth by remember { mutableStateOf(YearMonth.from(initialDate)) }
    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            // ==== HEADER WITH MONTH + YEAR DROPDOWN ====
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { currentMonth = currentMonth.minusMonths(1) }) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Previous"
                    )
                }

                // Month name
                Text(
                    text = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                // Year dropdown
                var expanded by remember { mutableStateOf(false) }
                Box {
                    TextButton(onClick = { expanded = true }) {
                        Text(
                            text = currentMonth.year.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Select year")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        val years = (1900..2100).toList()
                        years.forEach { year ->
                            DropdownMenuItem(
                                text = { Text(year.toString()) },
                                onClick = {
                                    expanded = false
                                    // clamp day if needed
                                    val safeDay =
                                        selectedDate.dayOfMonth.coerceAtMost(
                                            YearMonth.of(year, currentMonth.month).lengthOfMonth()
                                        )
                                    currentMonth = YearMonth.of(year, currentMonth.month)
                                    selectedDate = LocalDate.of(year, currentMonth.month, safeDay)
                                }
                            )
                        }
                    }
                }

                IconButton(onClick = { currentMonth = currentMonth.plusMonths(1) }) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Next"
                    )
                }
            }

            // ==== DAYS HEADER ====
            DaysOfWeekRow()

            // ==== DATES GRID ====
            DatesGrid(
                currentMonth = currentMonth,
                selectedDate = selectedDate,
                onDateSelected = { selectedDate = it }
            )

            // ==== ACTIONS ====
            DatePickerBottomActions(
                onDone = {
                    val maxDay = currentMonth.lengthOfMonth()
                    val safeDay = selectedDate.dayOfMonth.coerceAtMost(maxDay)
                    val finalDate =
                        LocalDate.of(currentMonth.year, currentMonth.month, safeDay)
                    onConfirm(finalDate)
                    onDismiss()
                    Toast.makeText(context, "Selected: $finalDate", Toast.LENGTH_SHORT).show()
                },
                onCancel = onDismiss
            )
        }
    }
}







@Preview(showBackground = true)
@Composable
fun PreviewCustomDatePicker() {
    var selectedDate by remember { mutableStateOf(LocalDate.of(2025, 4, 25)) }

//    CustomDatePicker(
//        selectedDate = selectedDate,
//        onDateSelected = { selectedDate = it },
//        onDismiss = { /* Do nothing for preview */ },
//        onDone = { /* Do nothing for preview */ }
//    )

    CustomDatePickerBottomSheet(
        initialDate = LocalDate.of(2025, 4, 25), // Example selected date
        onDismiss = { /* preview dismiss */ },
        onConfirm = { selectedDate ->
            println("Selected date: $selectedDate") // Logs in preview
        }
    )
}

