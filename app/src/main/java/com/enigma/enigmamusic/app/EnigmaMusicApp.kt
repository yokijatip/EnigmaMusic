@file:OptIn(ExperimentalMaterial3Api::class)

package com.enigma.enigmamusic.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.enigma.enigmamusic.ui.component.BottomNavigation
import com.enigma.enigmamusic.ui.navigation.Screen
import com.enigma.enigmamusic.ui.screen.home.HomeScreen
import com.enigma.enigmamusic.ui.screen.profile.ProfileScreen
import com.enigma.enigmamusic.ui.screen.search.SearchScreen
import com.enigma.enigmamusic.ui.theme.EnigmaMusicTheme

@Composable
fun EnigmaMusicApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailMusic.route) {
                BottomNavigation(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigationToDetail = { musicId ->
                        navController.navigate(Screen.DetailMusic.createRoute(musicId))
                    }
                )
            }

            composable(Screen.Search.route) {
                SearchScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun EnigmaMusicAppPreview() {
    EnigmaMusicTheme {
        EnigmaMusicApp()
    }
}
