package com.uvg.rueda.lab08.locations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uvg.rueda.lab08.data.Location
import com.uvg.rueda.lab08.data.LocationDb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(onLocationSelected: (Int) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Locations") }) }
    ) { paddingValues ->
        val locations = LocationDb().getAllLocations()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(locations) { location ->
                LocationItem(location = location, onClick = { onLocationSelected(location.id) })
            }
        }
    }
}

@Composable
fun LocationItem(location: Location, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Column {
            Text(text = location.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = location.type, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
