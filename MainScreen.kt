package com.huginbox.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.huginbox.data.*
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val db = ProductDatabase.getDatabase(context)
    val dao = db.productDao()
    val scope = rememberCoroutineScope()
    var products by remember { mutableStateOf(listOf<Product>()) }
    var householdId by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        HouseholdManager.getHouseholdId { id ->
            if (id != null) {
                householdId = id
                scope.launch {
                    products = dao.getAll(householdId)
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Produkty w gospodarstwie", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(products) { product ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("🧾 ${product.name}")
                        Text("📦 Ilość: ${product.quantity}")
                        Text("📅 Termin: ${product.expiryDate}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { navController.navigate("add") }) {
                Text("Dodaj produkt")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { navController.navigate("settings") }) {
                Text("Ustawienia")
            }
        }
    }
}
