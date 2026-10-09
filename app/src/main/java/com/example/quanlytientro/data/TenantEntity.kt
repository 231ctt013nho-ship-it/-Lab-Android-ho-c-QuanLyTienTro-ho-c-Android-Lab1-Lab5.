package com.example.quanlytientro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tenants")
data class TenantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phone: String,
    val email: String,
    val roomId: Int
)