package com.enigma.enigmamusic.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination

import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.enigma.enigmamusic.R
import com.enigma.enigmamusic.ui.navigation.NavigationItem
import com.enigma.enigmamusic.ui.navigation.Screen

@Composable
fun BottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(

            NavigationItem(
                title = stringResource(R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),

            NavigationItem(
                title = stringResource(R.string.search),
                icon = Icons.Default.Search,
                screen = Screen.Search
            ),

            NavigationItem(
                title = stringResource(R.string.profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            ),

            )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}