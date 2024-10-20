package com.uvg.rueda.lab08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uvg.rueda.lab08.characterdetail.characterDetailNavigation
import com.uvg.rueda.lab08.characters.CharactersViewModel
import com.uvg.rueda.lab08.characters.charactersNavigation
import com.uvg.rueda.lab08.data.AppDatabase
import com.uvg.rueda.lab08.data.CharacterDao
import com.uvg.rueda.lab08.data.CharacterRepository
import com.uvg.rueda.lab08.data.LocationDao
import com.uvg.rueda.lab08.data.LocationRepository
import com.uvg.rueda.lab08.data.UserRepository
import com.uvg.rueda.lab08.locations.LocationsViewModel
import com.uvg.rueda.lab08.locations.locationsNavigation
import com.uvg.rueda.lab08.login.LoginViewModel
import com.uvg.rueda.lab08.login.LoginViewModelFactory
import com.uvg.rueda.lab08.login.loginNavigation
import com.uvg.rueda.lab08.navigation.BottomNavigationBar
import com.uvg.rueda.lab08.navigation.Routes
import com.uvg.rueda.lab08.profile.ProfileScreen
import com.uvg.rueda.lab08.ui.theme.Lab08Theme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var characterDao: CharacterDao
    private lateinit var locationDao: LocationDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getInstance(applicationContext)
        characterDao = database.characterDao()
        locationDao = database.locationDao()

        val userRepository = UserRepository(applicationContext)
        val characterRepository = CharacterRepository(characterDao)
        val locationRepository = LocationRepository(locationDao)

        val viewModelFactory = LoginViewModelFactory(userRepository, characterRepository, locationRepository)
        val loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        val charactersViewModel = ViewModelProvider(this)[CharactersViewModel::class.java]
        val locationsViewModel = ViewModelProvider(this)[LocationsViewModel::class.java]

        lifecycleScope.launch {
            val userName = userRepository.userNameFlow.first()
        setContent {
            Lab08App(
                loginViewModel = loginViewModel,
                charactersViewModel = charactersViewModel,
                locationsViewModel = locationsViewModel,
                startDestination = if (userName != null) Routes.Characters.route else Routes.Login.route
            )
        }
    }}
}

@Composable
fun Lab08App(
    loginViewModel: LoginViewModel,
    charactersViewModel: CharactersViewModel,
    locationsViewModel: LocationsViewModel,
    startDestination: String
) {
    val userName by loginViewModel.nameState.collectAsState()

    Lab08Theme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        Scaffold(
            bottomBar = {
                if (navBackStackEntry?.destination?.route != Routes.Login.route) {
                    BottomNavigationBar(navController)
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.padding(paddingValues)
            ) {
                loginNavigation(navController, loginViewModel)
                charactersNavigation(navController, charactersViewModel)
                locationsNavigation(navController, locationsViewModel)
                characterDetailNavigation(navController)
                composable(Routes.Profile.route) {
                    ProfileScreen(viewModel = loginViewModel, onLogout = {
                        navController.navigate(Routes.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    })
                }
            }
        }
    }
}
