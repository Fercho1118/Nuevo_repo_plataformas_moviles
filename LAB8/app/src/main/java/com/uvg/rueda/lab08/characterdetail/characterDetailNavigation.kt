package com.uvg.rueda.lab08.characterdetail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uvg.rueda.lab08.navigation.Routes

fun NavGraphBuilder.characterDetailNavigation(navController: NavHostController) {
    composable(route = "${Routes.CharacterDetail.route}/{characterId}") { backStackEntry ->
        val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()

        if (characterId != null) {
            CharacterDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
}}