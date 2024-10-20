package com.uvg.rueda.lab08.locations

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uvg.rueda.lab08.navigation.Routes

fun NavGraphBuilder.locationsNavigation(
    navController: NavHostController,
    locationsViewModel: LocationsViewModel
) {
    composable(route = Routes.Locations.route) {
        LocationsScreen(
            viewModel = locationsViewModel,
            onLocationSelected = { locationId ->
                navController.navigate("${Routes.LocationDetail.route}/$locationId")
            }
        )
    }

    composable(route = "${Routes.LocationDetail.route}/{locationId}") { backStackEntry ->
        val locationId = backStackEntry.arguments?.getString("locationId")?.toIntOrNull()

        if (locationId != null) {
            val viewModelFactory = LocationDetailViewModelFactory(locationId)
            val locationDetailViewModel: LocationDetailViewModel = viewModel(factory = viewModelFactory)

            LocationDetailScreen(
                viewModel = locationDetailViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}