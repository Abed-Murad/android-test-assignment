package com.example.shacklehotelbuddy.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.featurs.home.RecentSearchesList
import com.example.shacklehotelbuddy.model.SearchQuery
import com.example.shacklehotelbuddy.repo.fakeSearchQuires
import com.example.shacklehotelbuddy.toReadableString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchQueryItem(
    modifier: Modifier = Modifier,
    searchQuery: SearchQuery,
    onSearchQuerySelected: (SearchQuery) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
        , onClick = {onSearchQuerySelected(searchQuery)}
    ) {
        Row (modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)){
            Icon(
                painter = painterResource(R.drawable.manage_history),
                contentDescription = "",
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = stringResource(
                    R.string.two_strings_template,
                    searchQuery.checkInDate.toReadableString(),
                    searchQuery.checkoutDate.toReadableString()
                ),
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            )
            Text(
                text = stringResource(
                    R.string.adult_children_template,
                    searchQuery.adultsCount,
                    searchQuery.childrenCount
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}
@Preview
@Composable
fun SearchQueryItemPreview() {
    RecentSearchesList(
        modifier = Modifier,
        searchHistory = fakeSearchQuires,
        onSearchQuerySelected = {}
    )
}

