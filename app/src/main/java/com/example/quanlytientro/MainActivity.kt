package com.example.quanlytientro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.quanlytientro.data.RoomEntity
import com.example.quanlytientro.ui.theme.QuanLyTienTroTheme
import com.example.quanlytientro.ui.theme.compose.MainHomeScreen
import com.example.quanlytientro.ui.theme.compose.RentalUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuanLyTienTroTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Tạo dữ liệu giả định trạng thái Success cho Lab 5
                    val sampleRooms = listOf(
                        RoomEntity(id = 1, roomNumber = "101", price = 1500000.0, isOccupied = true),
                        RoomEntity(id = 2, roomNumber = "102", price = 1500000.0, isOccupied = false)
                    )

                    // Truyền UiState.Success vào màn hình chính
                    MainHomeScreen(
                        uiState = RentalUiState.Success(sampleRooms),
                        onRetry = { }
                    )
                }
            }
        }
    }
}