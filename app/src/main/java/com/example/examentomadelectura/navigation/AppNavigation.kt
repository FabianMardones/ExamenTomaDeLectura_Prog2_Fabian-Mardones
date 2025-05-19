package com.example.examentomadelectura.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.examentomadelectura.ui.add_reading.AddReadingScreen
import com.example.examentomadelectura.ui.reading_list.ReadingListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.ReadingList.route) {
        composable(Screen.ReadingList.route) {
            ReadingListScreen(
                onNavigateToAddReading = { navController.navigate(Screen.AddReading.route) }
            ) // Pass the lambda here
        }
        composable(Screen.AddReading.route) {
            AddReadingScreen(
                onNavigateBack = { navController.popBackStack() }
            ) // And here
        }
    }
}
