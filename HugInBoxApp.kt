package com.huginbox.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.huginbox.ui.screens.*

@Composable
fun HugInBoxApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main") { MainScreen(navController) }
        composable("add") { AddProductScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("household") { HouseholdScreen(navController) } // 🆕 Dodana trasa
    }
}
