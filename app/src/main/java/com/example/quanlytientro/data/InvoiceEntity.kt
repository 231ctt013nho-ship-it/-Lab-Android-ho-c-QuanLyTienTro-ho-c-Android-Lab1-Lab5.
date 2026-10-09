package com.example.quanlytientro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoices")
data class InvoiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tenantId: Int,
    val amount: Double,
    val isPaid: Boolean
)