package com.example.quanlytientro.data

import kotlinx.coroutines.flow.Flow

class RentalRepository(private val roomDao: RoomDao) {
    val allRooms: Flow<List<RoomEntity>> = roomDao.getAllRooms()

    suspend fun insertRoom(room: RoomEntity) {
        roomDao.insertRoom(room)
    }
}