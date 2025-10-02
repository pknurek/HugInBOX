package com.huginbox.ui.screens

import android.content.res.Configuration
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import java.util.*

@Composable
fun SettingsScreen(navController: NavController) {
    var selectedLanguage by remember { mutableStateOf("pl") }

    Column {
        Text("Wybierz język:")
        Row {
            Button(onClick = { selectedLanguage = "pl"; updateLocale("pl") }) {
                Text("Polski")
            }
            Button(onClick = { selectedLanguage = "en"; updateLocale("en") }) {
                Text("English")
            }
        }
        Button(onClick = { navController.navigate("login") }) {
            Text("Wyloguj się")
        }
    }
}

fun updateLocale(language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    // W Android Studio: restart aktywności wymagany
}
