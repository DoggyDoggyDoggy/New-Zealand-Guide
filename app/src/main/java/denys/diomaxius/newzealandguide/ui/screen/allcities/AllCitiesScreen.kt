package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import denys.diomaxius.newzealandguide.domain.model.City
import denys.diomaxius.newzealandguide.ui.components.MenuButton
import denys.diomaxius.newzealandguide.ui.components.TopBar

@Composable
fun AllCitiesScreen(
    viewModel: AllCitiesScreenViewModel = hiltViewModel()
) {
    val cities = viewModel.lazyCities.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopBar(
                text = "All cities",
                navigationButton = {
                    MenuButton { }
                }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            cities = cities
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    cities: LazyPagingItems<City>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(cities.itemCount) { index ->
            cities[index]?.let { city ->
                CityItem(city = city)
            }
        }
    }
}

@Composable
fun CityItem(city: City) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Text(
            text = city.name,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityItemsPreview() {
    val cities = listOf<City>(
        City(
            id = "1",
            name = "Auckland",
            photos = emptyList()
        ),
        City(
            id = "2",
            name = "Wellington",
            photos = emptyList()
        ),
        City(
            id = "3",
            name = "Christchurch",
            photos = emptyList()
        ),
        City(
            id = "4",
            name = "Hamilton",
            photos = emptyList()
        ),
        City(
            id = "5",
            name = "Tauranga",
            photos = emptyList()
        )
    )
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items(cities){
            CityItem(it)
        }
    }
}