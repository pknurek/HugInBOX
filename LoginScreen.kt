package com.huginbox.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()

    Column {
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Hasło") })
        Button(onClick = {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { navController.navigate("main") }
                .addOnFailureListener { /* obsługa błędu */ }
        }) {
            Text("Zaloguj się")
        }
    }
}
