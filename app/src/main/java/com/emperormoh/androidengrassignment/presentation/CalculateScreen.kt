package com.emperormoh.androidengrassignment.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emperormoh.androidengrassignment.R
import com.emperormoh.androidengrassignment.ui.theme.AndroidEngrAssignmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateScreen(
    onBackClick: () -> Unit,
    onNavigate: () -> Unit
) {
    val contentOffsetY = remember { Animatable(300f) } // start below screen

    // Animate when composable is launched
    LaunchedEffect(Unit) {
        contentOffsetY.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 600,
                easing = FastOutSlowInEasing
            )
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically),
                title = {
                    Text(
                        "Calculate",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 10.dp).clickable{ onBackClick()},
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6A1B9A))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .offset(y = contentOffsetY.value.dp)
                .fillMaxSize().background(Color(0xFFF7F7F8))
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                // Destination Section
                Text("Destination", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(5.dp))
                DestinationInputSection()
                Spacer(Modifier.height(10.dp))
                // Packaging
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Packaging", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("What are you sending?", fontSize = 14.sp, color = Color.Gray)
                    PackagingDropdown()
                }

                Spacer(Modifier.height(10.dp))
                // Categories
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Categories", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("What are you sending?", fontSize = 14.sp, color = Color.Gray)
                    CategoryChips()
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onNavigate,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Calculate", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@Composable
fun DestinationInputSection() {
    val senderLocation = remember { mutableStateOf("") }
    val receiverLocation = remember { mutableStateOf("") }
    val approxWeight = remember { mutableStateOf("") }

    val fields = listOf(
        Triple(Icons.Default.UploadFile, "Sender location", senderLocation),
        Triple(Icons.Default.FileDownload, "Receiver location", receiverLocation),
        Triple(Icons.Default.Scale, "Approx weight", approxWeight)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        fields.forEach { (icon, label, state) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color(0xFFF7F7F8), shape = RoundedCornerShape(8.dp))
                    .padding(start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(12.dp))
                VerticalDivider(
                    modifier = Modifier
                        .width(1.dp)
                        .height(24.dp),
                    color = Color.Gray.copy(alpha = 0.3f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                BasicTextField(
                    value = state.value,
                    onValueChange = { state.value = it },
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            Modifier
                                .padding(vertical = 12.dp)
                                .weight(1f)
                        ) {
                            if (state.value.isEmpty()) {
                                Text(
                                    text = label,
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun PackagingDropdown(selectedItem: String = "Box") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_box), // Replace with your own icon
            contentDescription = "Package Icon",
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(selectedItem, fontSize = 14.sp)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Dropdown",
            tint = Color.Gray
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryChips() {
    var selectedSuggestedAmountChip by remember { mutableStateOf<String?>(null) }
    val categories = listOf("Documents", "Glass", "Liquid", "Food", "Electronic", "Product", "Others")
    FlowRow{
        categories.forEach { value ->
            ClickableChip(
                value = value,
                isSelected = value == selectedSuggestedAmountChip,
                onClick = { selectedSuggestedAmountChip = value }
            )
            Spacer(Modifier.width(10.dp))
        }
    }
}

@Composable
fun ClickableChip(
    modifier: Modifier = Modifier,
    value: String,
    isSelected: Boolean = false,
    onClick: (String) -> Unit
){
    val backgroundColor = if (isSelected) Color.Black else Color.White
    val borderColor = Color.Gray
    val textColor = if (isSelected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .padding(vertical = 5.dp)
            //.wrapContentSize()
            .sizeIn(minWidth = 60.dp, minHeight = 40.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(6.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(6.dp),
                color = borderColor
            )
            .clickable { onClick(value) },
        contentAlignment = Alignment.Center
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelected){
                Spacer(Modifier.width(10.dp))
                Icon(
                    modifier = modifier.size(18.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = "Dropdown",
                    tint = Color.White
                )
            }
            Text(
                text = value,
                modifier = modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CalculateScreenPreview() {
    AndroidEngrAssignmentTheme {
        CalculateScreen(onBackClick = {}, onNavigate = {})
    }
}