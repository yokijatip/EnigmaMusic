package com.enigma.enigmamusic.ui.navigation

sealed class Screen(
    val route: String
) {

    object Home : Screen("home")
    object Search : Screen("search")
    object Profile : Screen("profile")
    object DetailMusic : Screen("home/{musicId}") {
        fun createRoute(musicId: Long) = "home/$musicId"
    }

}