package com.uvg.rueda.lab08.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.uvg.rueda.lab08.navigation.Routes

fun NavGraphBuilder.loginNavigation(
    navController: NavHostController,
    viewModel: LoginViewModel
) {
    composable(route = Routes.Login.route) {
        LoginScreen(
            viewModel = viewModel,
            onNavigateToCharacters = {
                navController.navigate(Routes.Characters.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            }
        )
    }
}