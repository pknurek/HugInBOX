package com.huginbox.data

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

object OpenFoodFactsApi {
    private val client = OkHttpClient()

    fun getProductName(barcode: String, onResult: (String?) -> Unit) {
        val url = "https://world.openfoodfacts.org/api/v0/product/$barcode.json"
        val request = Request.Builder().url(url).build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return onResult(null)
            val body = response.body?.string() ?: return onResult(null)
            val json = JSONObject(body)
            val name = json.optJSONObject("product")?.optString("product_name")
            onResult(name)
        }
    }
}
