package com.example.examentomadelectura.navigation

sealed class Screen(val route: String) {
    object ReadingList : Screen("reading_list")
    object AddReading : Screen("add_reading")
}
