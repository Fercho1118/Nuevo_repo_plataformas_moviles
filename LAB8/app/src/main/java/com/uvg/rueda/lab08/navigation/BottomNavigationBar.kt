package com.uvg.rueda.lab08.navigation

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uvg.rueda.lab08.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val routes = listOf(
        Routes.Characters.route,
        Routes.Locations.route,
        Routes.Profile.route
    )

    val labels = listOf("Characters", "Locations", "Profile")

    val icons = listOf(
        painterResource(id = R.drawable.group),
        painterResource(id = R.drawable.baseline_public_24),
        Icons.Default.Person
    )

    NavigationBar(
        containerColor = Color(0xFF5A839D),
        contentColor = Color.White
    ) {
        routes.forEachIndexed { index, route ->
            NavigationBarItem(
                icon = {
                    when (val icon = icons[index]) {
                        is androidx.compose.ui.graphics.painter.Painter -> Image(
                            painter = icon,
                            contentDescription = labels[index]
                        )
                        is androidx.compose.ui.graphics.vector.ImageVector -> Icon(
                            imageVector = icon,
                            contentDescription = labels[index]
                        )
                    }
                },
                label = { Text(labels[index]) },
                selected = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color(0xFFD3E3E8),
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color(0xFFD3E3E8)
                ),
                onClick = {
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    BottomNavigationBar(navController = navController)
}