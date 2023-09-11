package com.example.shacklehotelbuddy.featurs.home

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.model.SearchQuery
import com.example.shacklehotelbuddy.repo.fakeSearchQuires
import com.example.shacklehotelbuddy.toReadableString
import com.example.shacklehotelbuddy.ui.components.SearchQueryItem
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onSearchButtonClicked: () -> Unit,
    onSearchQuerySelected: (SearchQuery) -> Unit
) {


    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.getSearchHistory()
        }
    }

    val uiState by viewModel.uiState.collectAsState()

    HomeScreenLayout(
        uiState = uiState,
        onSearchButtonClicked = {
            viewModel.addSearchQuery(viewModel.getSearchQuery())
            onSearchButtonClicked()
        },
        onCheckInDateUpdated = { date -> viewModel.updateCheckInDate(date) },
        onCheckoutDateUpdated = { date -> viewModel.updateCheckoutDate(date) },
        onAdultsCountUpdated = { adultsCount -> viewModel.updateAdultsCount(adultsCount) },
        onChildrenCountUpdated = { childrenCount -> viewModel.updateChildrenCount(childrenCount) }
    ) { searchQuery -> onSearchQuerySelected(searchQuery) }
}


@Composable
fun HomeScreenLayout(
    uiState: MyState,
    onSearchButtonClicked: () -> Unit,
    onCheckInDateUpdated: (Date) -> Unit,
    onCheckoutDateUpdated: (Date) -> Unit,
    onAdultsCountUpdated: (Int) -> Unit,
    onChildrenCountUpdated: (Int) -> Unit,
    onSearchQuerySelected: (SearchQuery) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillWidth
            ),
    ) {
        Column {
            HeadLine(modifier = Modifier.padding(top = 110.dp, start = 16.dp, end = 16.dp))
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

            ) {
                Column(modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
                    ) {
                        CheckInDateLabel(
                            label = stringResource(id = R.string.check_in_date),
                            icon = R.drawable.event_upcoming,
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(.5f),
                        )
                        DatePickButton(
                            selectedDate = uiState.checkInDate,
                            onDateSelected = {
                                onCheckInDateUpdated(it)
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(.5f),
                        )
                    }
                    Row(Modifier.fillMaxWidth()) {
                        CheckInDateLabel(
                            label = stringResource(R.string.check_out_date),
                            icon = R.drawable.event_available,
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(.5f),
                        )
                        DatePickButton(
                            selectedDate = uiState.checkInDate,
                            onDateSelected = {
                                onCheckoutDateUpdated(it)
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(.5f),
                        )
                    }
                    Row(Modifier.fillMaxWidth()) {
                        CheckInDateLabel(
                            label = stringResource(R.string.adults), icon = R.drawable.person,
                            modifier = Modifier
                                .weight(.5f)
                                .padding(start = 16.dp)
                                .align(Alignment.CenterVertically),
                        )

                        InputField(
                            uiState.adultsCount,
                            onCountChange = { onAdultsCountUpdated(it) },
                            Modifier.weight(.5f)
                        )
                    }
                    Row(Modifier.fillMaxWidth()) {
                        CheckInDateLabel(
                            label = stringResource(R.string.children),
                            icon = R.drawable.supervisor_account,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp)
                                .align(Alignment.CenterVertically),
                        )

                        InputField(
                            uiState.childrenCount,
                            onCountChange = { onChildrenCountUpdated(it) },
                            Modifier.weight(1f)
                        )
                    }

                }

            }
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(R.string.recent_searches),
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 48.4.sp,
                    color = Color(0xFFFFFFFF),
                )
            )
            RecentSearchesList(uiState.searchHistory, onSearchQuerySelected)
            Button(
                onClick = {
                    onSearchButtonClicked()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.search))
            }
        }
    }

}

@Composable
fun RecentSearchesList(
    searchHistory: List<SearchQuery>,
    onSearchQuerySelected: (SearchQuery) -> Unit
) {
    LazyColumn(
        modifier = Modifier.height(180.dp),
        verticalArrangement = Arrangement.spacedBy(-16.dp),
    ) {
        items(items = searchHistory, itemContent = { item ->
            SearchQueryItem(
                modifier = Modifier.padding(16.dp),
                searchQuery = item,
                onSearchQuerySelected
            )
        })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    initialValue: Int,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier,
        value = initialValue.toString(),
        maxLines = 1,
        onValueChange = { onCountChange(it.toIntOrNull() ?: 0) },
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}


@Composable
fun DatePickButton(
    selectedDate: Date,
    onDateSelected: (Date) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            val myCalendar = Calendar.getInstance()
            myCalendar.set(Calendar.YEAR, selectedYear)
            myCalendar.set(Calendar.MONTH, selectedMonth)
            myCalendar.set(Calendar.DAY_OF_MONTH, selectedDayOfMonth)
            val newSelectedDate: Date = myCalendar.time
            onDateSelected(newSelectedDate)
        }, year, month, dayOfMonth
    )
    datePicker.datePicker.minDate = calendar.timeInMillis


    var enabled by remember { mutableStateOf(true) }
    ClickableText(
        modifier = modifier,
        text = AnnotatedString(selectedDate.toReadableString()),
        onClick = {
            if (enabled) {
                enabled = false
            }
            datePicker.show()
        })
}


@Composable
fun HeadLine(modifier: Modifier = Modifier) {
    Text(
        text = "Select guests, date and time",
        style = TextStyle(
            fontSize = 44.sp,
            lineHeight = 48.4.sp,
            color = Color(0xFFFFFFFF),
        ), modifier = modifier
    )
}


@Composable
fun CheckInDateLabel(
    label: String,
    icon: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "",
        )
        Text(
            text = label,
            style = TextStyle(
                color = Color(0xFF6D6D6D),
            ),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@Preview
@Composable
fun HomeScreenLayoutPreview() {
    val uiState = MyState(searchHistory = fakeSearchQuires)
    HomeScreenLayout(
        uiState = uiState,
        onSearchButtonClicked = {},
        onCheckInDateUpdated = {},
        onCheckoutDateUpdated = {},
        onAdultsCountUpdated = {},
        onChildrenCountUpdated = {}
    ) {}
}


