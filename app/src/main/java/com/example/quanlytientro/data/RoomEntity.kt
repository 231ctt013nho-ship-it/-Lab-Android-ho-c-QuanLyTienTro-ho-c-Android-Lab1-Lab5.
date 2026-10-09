package com.example.quanlytientro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class RoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val roomNumber: String,
    val price: Double,
    val isOccupied: Boolean
)