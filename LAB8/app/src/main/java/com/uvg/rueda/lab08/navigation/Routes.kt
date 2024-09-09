package com.uvg.rueda.lab08.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Characters : Routes("characters")
    object CharacterDetail : Routes("character_detail")
}