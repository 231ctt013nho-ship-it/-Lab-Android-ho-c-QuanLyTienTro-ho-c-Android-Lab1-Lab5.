package com.example.quanlytientro.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RoomEntity::class, TenantEntity::class, InvoiceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao
}