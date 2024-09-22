package com.uvg.rueda.lab08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uvg.rueda.lab08.characterdetail.characterDetailNavigation
import com.uvg.rueda.lab08.characters.charactersNavigation
import com.uvg.rueda.lab08.login.loginNavigation
import com.uvg.rueda.lab08.locations.locationsNavigation
import com.uvg.rueda.lab08.navigation.BottomNavigationBar
import com.uvg.rueda.lab08.navigation.Routes
import com.uvg.rueda.lab08.profile.ProfileScreen
import com.uvg.rueda.lab08.ui.theme.Lab08Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab08App()
        }
    }
}

@Composable
fun Lab08App() {
    Lab08Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Scaffold(
                bottomBar = {
                    if (currentRoute != Routes.Login.route) {
                        BottomNavigationBar(navController)
                    }
                }
            ) { paddingValues ->
                AppNavigation(navController = navController, modifier = Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Routes.Login.route, modifier = modifier) {
        loginNavigation(navController)
        charactersNavigation(navController)
        characterDetailNavigation(navController)
        locationsNavigation(navController)
        composable(route = Routes.Profile.route) {
            ProfileScreen(onLogout = {
                navController.navigate(Routes.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            })
        }
    }
}