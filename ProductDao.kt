package com.huginbox.data

import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE householdId = :householdId")
    suspend fun getAll(householdId: String): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Delete
    suspend fun delete(product: Product)
}
