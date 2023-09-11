package com.example.shacklehotelbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shacklehotelbuddy.featurs.details.DetailsScreen
import com.example.shacklehotelbuddy.featurs.details.SearchResultsViewModel
import com.example.shacklehotelbuddy.featurs.home.HomeScreen
import com.example.shacklehotelbuddy.featurs.home.HomeViewModel
import com.example.shacklehotelbuddy.model.SearchQuery
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val searchResultsViewModel: SearchResultsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShackleHotelBuddyTheme {
                Scaffold { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        InitNavHost()
                    }
                }
            }
        }
    }

    @Composable
    private fun InitNavHost() {
        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = HOME_SCREEN_ROUTE
        ) {
            composable(route = HOME_SCREEN_ROUTE) {
                val gson: Gson = GsonBuilder().create()
                val searchQueryGson = gson.toJson(homeViewModel.getSearchQuery())
                HomeScreen(viewModel = homeViewModel, onSearchButtonClicked = {
                    navController.navigate(
                        SEARCH_RESULTS_SCREEN_ROUTE.replace(
                            "{searchQuery}", searchQueryGson
                        )
                    )
                }, onSearchQuerySelected = {
                    navController.navigate(
                        SEARCH_RESULTS_SCREEN_ROUTE.replace(
                            "{searchQuery}", searchQueryGson
                        )
                    )
                })
            }
            composable(
                route = SEARCH_RESULTS_SCREEN_ROUTE,
            ) {
                val gson: Gson = GsonBuilder().create()
                val searchQueryGson = it.arguments?.getString("searchQuery")
                val searchQuery = gson.fromJson(searchQueryGson, SearchQuery::class.java)

                DetailsScreen(
                    searchResultsViewModel, navController, searchQuery
                )
            }
        }
    }
}
