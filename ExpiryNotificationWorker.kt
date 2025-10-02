package com.huginbox.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.huginbox.R
import com.huginbox.data.ProductDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class ExpiryNotificationWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        val db = ProductDatabase.getDatabase(applicationContext)
        val products = db.productDao().getAll("dom123") // tymczasowo

        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        products.forEach { product ->
            val expiry = LocalDate.parse(product.expiryDate, formatter)
            if (expiry.minusDays(3) == today) {
                showNotification(product.name)
            }
        }

        return Result.success()
    }

    private fun showNotification(productName: String) {
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "expiry_channel"

        val channel = NotificationChannel(channelId, "Expiry Alerts", NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Produkt wygasa")
            .setContentText("Termin dla $productName zbliża się!")
            .setSmallIcon(R.drawable.ic_notification)
            .build()

        manager.notify(productName.hashCode(), notification)
    }
}
