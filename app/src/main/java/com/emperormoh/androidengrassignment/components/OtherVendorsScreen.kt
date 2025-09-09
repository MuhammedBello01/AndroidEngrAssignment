package com.emperormoh.androidengrassignment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emperormoh.androidengrassignment.R

@Composable
fun OtherVendorsScreen(vendors: List<Vendor>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Other Vendors",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(vendors) { vendor ->
                VendorCard(vendor)
            }
        }
    }
}

//@Composable
//fun VendorCard(vendor: Vendor) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//        Box(contentAlignment = Alignment.TopEnd) {
//            Image(
//                painter = painterResource(id = vendor.logoRes),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(64.dp)
//                    .clip(CircleShape)
//                    .background(Color.White),
//                contentScale = ContentScale.Crop
//            )
//
//            if (vendor.discountPercent != null) {
//                Box(
//                    modifier = Modifier
//                        .offset(x = (8).dp, y = (-8).dp)
//                        .background(color = Color(0xFFD2E9FB),
//                            shape = RoundedCornerShape(
//                                topStart = 0.dp,
//                                topEnd = 8.dp,
//                                bottomEnd = 0.dp,
//                                bottomStart = 8.dp
//                            ))
//                        .padding(horizontal = 6.dp, vertical = 2.dp)
//                ) {
//                    Text(
//                        text = "${vendor.discountPercent}% off",
//                        fontSize = 10.sp,
//                        color = Color.Black,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Text(
//            text = vendor.name,
//            style = MaterialTheme.typography.bodySmall,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis,
//            textAlign = TextAlign.Center
//        )
//    }
//}

@Composable
fun VendorCard(vendor: Vendor) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Image(
                painter = painterResource(id = vendor.logoRes),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )

            if (vendor.discountPercent != null) {
                // OUTER WHITE CONTAINER
                Box(
                    modifier = Modifier
                        .offset(x = 8.dp, y = (-8).dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 8.dp,
                                bottomEnd = 0.dp,
                                bottomStart = 8.dp
                            )
                        )
                        //.padding(2.dp) // Space between white outer and blue inner
                        .padding(
                            start = 2.dp,
                            bottom = 2.dp
                        )
                ) {
                    // INNER BLUE CONTAINER
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(0xFFD2E9FB),
                                shape = RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 8.dp,
                                    bottomEnd = 0.dp,
                                    bottomStart = 8.dp
                                )
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "${vendor.discountPercent}% off",
                            fontSize = 8.sp,
                            lineHeight = 8.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = vendor.name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun VendorCard2(
    modifier: Modifier = Modifier,
    vendor: Vendor
) {
    Box(
        modifier = modifier
            .width(100.dp)
            .wrapContentHeight()
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        // Vendor Logo and Name
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier.size(64.dp)
            ) {
                Image(
                    painter = painterResource(id = vendor.logoRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )

                // Discount Badge
                if (vendor.discountPercent != null){
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 8.dp, y = (-8).dp)
                            .background(Color(0xFFD2E9FB), RoundedCornerShape(8.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "${vendor.discountPercent}% off",
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = vendor.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}



data class Vendor(
    val name: String = "",
    val logoRes: Int = 0, // Or use a URL with Coil
    val discountPercent: Int? = null
)

@Preview(showBackground = true)
@Composable
fun PreviewOtherVendorsScreen() {
    val sampleVendors = listOf(
        Vendor("Carrabba’s Italian", R.drawable.ic_box, 30),
        Vendor("Carrabba’s Italian", R.drawable.ic_box, null),
        Vendor("Carrabba’s Italian", R.drawable.ic_box, 30),
        Vendor("Carrabba’s Italian", R.drawable.ic_box, null),
        Vendor("Carrabba’s Italian", R.drawable.ic_box, 30),
        Vendor("Carrabba’s Italian", R.drawable.ic_box, null),
        Vendor("Carrabba’s Italian", R.drawable.ic_box, 30),
        Vendor("Carrabba’s Italian", R.drawable.ic_box, null),
        Vendor("Carrabba’s Italian", R.drawable.ic_box, 30)
    )

    //OtherVendorsScreen(vendors = sampleVendors)
    Column{
        Spacer(Modifier.height(20.dp))
        VendorCard(sampleVendors.firstOrNull()?: Vendor())
        Spacer(Modifier.height(20.dp))
        VendorCard2(
            vendor = sampleVendors.firstOrNull() ?: Vendor(),
        )
    }

}
