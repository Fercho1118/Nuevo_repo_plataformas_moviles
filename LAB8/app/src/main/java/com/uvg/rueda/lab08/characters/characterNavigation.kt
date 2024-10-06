package com.uvg.rueda.lab08.characters

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uvg.rueda.lab08.characterdetail.CharacterDetailScreen
import com.uvg.rueda.lab08.navigation.Routes

fun NavGraphBuilder.charactersNavigation(navController: NavHostController) {
    composable(route = Routes.Characters.route) {
        CharactersScreen(onCharacterSelected = { characterId ->
            navController.navigate("${Routes.CharacterDetail.route}/$characterId")
        })
    }
}