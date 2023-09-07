package com.example.shacklehotelbuddy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.model.Hotel
import kotlin.math.roundToInt


@Composable
fun HotelDetailsCard(
    hotelDetails: Hotel,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column {
            if (hotelDetails.imageUrl.isNotEmpty()) {
                Row {
                    HotelImage(hotelDetails.imageUrl)
                }
            }
            Box(
                modifier = Modifier.padding(vertical = 8.dp),
            ) {
                Column {
                    Row {
                        HotelName(
                            hotelDetails.name,
                            modifier = Modifier.height(24.dp),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        HotelRatting(hotelDetails.ratting)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        HotelLocation(hotelDetails.location,
                            Modifier.height(20.dp))
                    }
                    PricePerNight(hotelDetails.pricePerNight, Modifier.height(24.dp))
                }
            }
        }
    }
}


@Composable
fun HotelRatting(
    ratting: Double,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Rounded.Star, "",
            modifier = Modifier
                .padding(end = 6.dp)
                .size(16.dp)
        )
        Text(
            text = ratting.toString(),
            style = TextStyle(
                color = Color(0xFF000000),
            )
        )
    }
}


@Composable
fun HotelLocation(
    location: String,
    modifier: Modifier
) {
    Text(
        text = location,
        style = TextStyle(
            fontSize = 14.sp,
            color = Color(0xFF6D6D6D),
        ),
        modifier = modifier
    )
}

@Composable
fun PricePerNight(
    pricePerNight: Double,
    modifier: Modifier,
) {
    Text(
        text = "€ ${pricePerNight.roundToInt()} night",
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF000000),
        ), 
        modifier = modifier
    )
}


@Composable
fun HotelImage(
    headerImageUrl: String?,
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = headerImageUrl,
        onState = { state ->
            isLoading = state is AsyncImagePainter.State.Loading
            isError = state is AsyncImagePainter.State.Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
                color = MaterialTheme.colorScheme.tertiary,
            )
        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && !isLocalInspection) {
                imageLoader
            } else {
                painterResource(R.drawable.ic_placeholder_default)
            },
            contentDescription = null
        )
    }
}

@Composable
fun HotelName(
    hotelName: String,
    modifier: Modifier = Modifier,
) {
    Text(
        hotelName,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}


@Preview
@Composable
fun HotelCardPreview() {
    HotelDetailsCard(
        Hotel(
            id = 1,
            "Beverly Hotel",
            "Amsterdam, Netherlands",
            "https://www.ahstatic.com/photos/1276_rodbb_00_p_2048x1536.jpg",
            4.5,
            100.0
        ),
    )
}

