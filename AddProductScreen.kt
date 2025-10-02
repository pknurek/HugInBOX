package com.huginbox.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.huginbox.data.Product
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AddProductScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nazwa") })
        OutlinedTextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Ilość") })
        OutlinedTextField(value = expiryDate, onValueChange = { expiryDate = it }, label = { Text("Termin") })
        Button(onClick = {
            val product = Product(
                name = name,
                barcode = "manual",
                quantity = quantity.toIntOrNull() ?: 1,
                expiryDate = expiryDate,
                householdId = "dom123" // tymczasowo
            )
            // zapisz do bazy
            navController.navigate("main")
        }) {
            Text("Zapisz")
        }
    }
}
