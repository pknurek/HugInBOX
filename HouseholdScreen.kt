package com.huginbox.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.huginbox.data.HouseholdManager

@Composable
fun HouseholdScreen(navController: NavController) {
    var code by remember { mutableStateOf("") }
    var householdId by remember { mutableStateOf("") }

    Column {
        Text("Dołącz do gospodarstwa:")
        OutlinedTextField(value = code, onValueChange = { code = it }, label = { Text("Kod gospodarstwa") })
        Button(onClick = {
            HouseholdManager.joinHousehold(code) { success ->
                if (success) householdId = code
            }
        }) {
            Text("Dołącz")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Lub utwórz nowe:")
        Button(onClick = {
            HouseholdManager.createHousehold { newId ->
                householdId = newId
            }
        }) {
            Text("Utwórz gospodarstwo")
        }

        if (householdId.isNotEmpty()) {
            Text("Twój kod gospodarstwa: $householdId")
        }
    }
}
