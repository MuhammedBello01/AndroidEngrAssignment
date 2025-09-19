@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emperormoh.androidengrassignment.components.CustomDatePickerBottomSheet2
import com.emperormoh.androidengrassignment.utils.marketCountdown
import java.time.LocalDate
import java.time.format.DateTimeFormatter



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

        val message = marketCountdown(
            openHour = 9,
            openMinute = 0,
            closeHour = 3,
            closeMinute = 0
        )
        Text(message)
        //Text("Selected Date: ${selectedDateQ.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))}")

        if (showDatePicker) {
            CustomDatePickerBottomSheet2(
                initialDate = LocalDate.now(), // Example selected date
                onDismiss = { showDatePicker = false},
                minDate = LocalDate.of(2025, 4, 29),
                maxDate = LocalDate.of(2026, 1, 10),
                onConfirm = { selectedDate ->
                    selectedDateQ = selectedDate
                    showDatePicker = false
                    println("Selected date: $selectedDate") // Logs in preview
                }
            )
        }
    }
}
