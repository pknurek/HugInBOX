package com.huginbox.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object HouseholdManager {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun getHouseholdId(onResult: (String?) -> Unit) {
        val userId = auth.currentUser?.uid ?: return onResult(null)
        db.collection("users").document(userId).get()
            .addOnSuccessListener { doc ->
                val householdId = doc.getString("householdId")
                onResult(householdId)
            }
            .addOnFailureListener { onResult(null) }
    }

    fun joinHousehold(code: String, onComplete: (Boolean) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        db.collection("users").document(userId)
            .update("householdId", code)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun createHousehold(onComplete: (String) -> Unit) {
        val newId = db.collection("households").document().id
        val userId = auth.currentUser?.uid ?: return
        db.collection("users").document(userId)
            .set(mapOf("householdId" to newId))
            .addOnSuccessListener { onComplete(newId) }
    }
}
