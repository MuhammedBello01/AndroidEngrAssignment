package com.emperormoh.androidengrassignment.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerBottomSheet2(
    modifier: Modifier = Modifier,
    initialDate: LocalDate = LocalDate.now(),
    minDate: LocalDate = LocalDate.now(),
    maxDate: LocalDate? = null,
    onDismiss: () -> Unit,
    onConfirm: (LocalDate) -> Unit
) {
    var selectedDate by remember { mutableStateOf(initialDate) }
    var currentMonth by remember { mutableStateOf(YearMonth.from(initialDate)) }
    var expanded by remember { mutableStateOf(false) }

    val safeMaxDate = maxDate ?: LocalDate.now().plusYears(10)//minDate.plusYears(50) // fallback max

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            // ==== HEADER ====
            Row(
                modifier = Modifier
                    .fillMaxWidth().size(25.dp)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        val prevMonth = currentMonth.minusMonths(1)
                        if (!prevMonth.atEndOfMonth().isBefore(minDate)) {
                            currentMonth = prevMonth
                        }
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Previous")
                }

                // Month + year selector
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(Modifier.width(2.dp))

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
                            val years = (minDate.year..safeMaxDate.year).toList()
                            years.forEach { year ->
                                DropdownMenuItem(
                                    text = { Text(year.toString()) },
                                    onClick = {
                                        expanded = false
                                        val safeDay = selectedDate.dayOfMonth.coerceAtMost(
                                            YearMonth.of(year, currentMonth.month).lengthOfMonth()
                                        )
                                        currentMonth = YearMonth.of(year, currentMonth.month)
                                        selectedDate = LocalDate.of(year, currentMonth.month, safeDay)
                                    }
                                )
                            }
                        }
                    }
                }

                IconButton(
                    onClick = {
                        val nextMonth = currentMonth.plusMonths(1)
                        if (!nextMonth.atDay(1).isAfter(safeMaxDate)) {
                            currentMonth = nextMonth
                        }
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Next")
                }
            }

            // ==== DAYS OF WEEK ====
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").forEach { day ->
                    Text(
                        text = day,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // ==== GRID ====
            val firstDayOfMonth = currentMonth.atDay(1)
            val daysInMonth = currentMonth.lengthOfMonth()
            val firstDayOffset = (firstDayOfMonth.dayOfWeek.value + 6) % 7 // shift so Mon=0

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                items(firstDayOffset) { Spacer(Modifier.size(40.dp)) }

                items(daysInMonth) { index ->
                    val date = currentMonth.atDay(index + 1)
                    val isSelected = date == selectedDate
                    val isToday = date == LocalDate.now()
                    val isDisabled =
                        date.isBefore(minDate) || (maxDate != null && date.isAfter(maxDate))

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isSelected -> Color.Red
                                    isToday -> Color.LightGray.copy(alpha = 0.5f)
                                    else -> Color.Transparent
                                }
                            )
                            .clickable(enabled = !isDisabled) {
                                if (!isDisabled) selectedDate = date
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (index + 1).toString(),
                            color = when {
                                isDisabled -> Color.Gray
                                isSelected -> Color.White
                                else -> Color.Black
                            }
                        )
                    }
                }
            }

            // ==== ACTIONS ====
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onConfirm(selectedDate)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) { Text("Done") }

                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = onDismiss
                ) { Text("Cancel") }
            }
        }
    }
}
