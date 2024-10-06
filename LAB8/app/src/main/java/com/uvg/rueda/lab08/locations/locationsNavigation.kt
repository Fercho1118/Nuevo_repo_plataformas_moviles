package com.uvg.rueda.lab08.locations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uvg.rueda.lab08.navigation.Routes

fun NavGraphBuilder.locationsNavigation(navController: NavHostController) {
    composable(route = Routes.Locations.route) {
        LocationsScreen(onLocationSelected = { locationId ->
            navController.navigate("${Routes.LocationDetail.route}/$locationId")
        })
    }
    composable(route = "${Routes.LocationDetail.route}/{locationId}") { backStackEntry ->
        val locationId = backStackEntry.arguments?.getString("locationId")?.toIntOrNull()

        if (locationId != null) {
            LocationDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
}}