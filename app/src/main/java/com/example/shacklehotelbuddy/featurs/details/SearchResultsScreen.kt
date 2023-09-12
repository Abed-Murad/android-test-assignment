package com.example.shacklehotelbuddy.featurs.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.model.Hotel
import com.example.shacklehotelbuddy.model.SearchQuery
import com.example.shacklehotelbuddy.repo.fakeListOfHotels
import com.example.shacklehotelbuddy.ui.components.HotelDetailsCard
import com.example.shacklehotelbuddy.ui.icons.Icons
import kotlinx.coroutines.launch


@Composable
fun DetailsScreen(
    viewModel: SearchResultsViewModel,
    navController: NavController,
    searchQuery: SearchQuery
) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.getHotelsList(searchQuery)
        }
    }
    val hotels by viewModel.hotels.collectAsState()

    SearchDetailsLayout(navController, hotels)
}


@Composable
fun SearchDetailsLayout(navController: NavController, hotels: List<Hotel>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column {
            SearchResultsTopBar(onBackClick = {
                navController.navigateUp()
            })
            SearchResultsList(hotels)
        }
    }
}


@Composable
fun SearchResultsList(hotels: List<Hotel>) {
    LazyColumn(
        modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items = hotels, itemContent = { item ->
            HotelDetailsCard(hotelDetails = item, onCLick = {

            })
        })
    }
}

@Composable
private fun SearchResultsTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.ArrowBack,
                contentDescription = stringResource(
                    id = R.string.back,
                ),
            )
        }
        Text(
            text = stringResource(R.string.search_results),
            fontSize = 18.sp,
            lineHeight = 20.sp,
            color = Color(0xFF000000),
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
        )
    }
}

@Preview()
@Composable
fun SearchDetailsScreenPreview() {
    val fakeListOfHotels = fakeListOfHotels
    SearchDetailsLayout(
        navController = rememberNavController(),
        hotels = fakeListOfHotels
    )
}

