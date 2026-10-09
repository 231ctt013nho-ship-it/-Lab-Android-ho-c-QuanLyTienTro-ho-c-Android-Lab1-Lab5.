
package com.example.quanlytientro.ui.theme.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class RoomUi(
    val roomName: String,
    val price: Long,
    val isOccupied: Boolean
)

@Composable
fun RoomListScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    // Dữ liệu mẫu để xây dựng giao diện trước
    val rooms = listOf(
        RoomUi("Phòng 101", 1500000L, true),
        RoomUi("Phòng 102", 1800000L, false),
        RoomUi("Phòng 103", 2000000L, true),
        RoomUi("Phòng 104", 1500000L, false)
    )

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Danh sách phòng",
                    style = MaterialTheme.typography.headlineSmall
                )

                Button(onClick = onBackClick) {
                    Text("Quay lại")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tổng số phòng: ${rooms.size}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(rooms) { room ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 3.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = room.roomName,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Tiền phòng: ${
                                    "%,d".format(room.price).replace(',', '.')
                                } VNĐ"
                            )

                            Text(
                                text = if (room.isOccupied) {
                                    "Trạng thái: Đã có người thuê"
                                } else {
                                    "Trạng thái: Còn trống"
                                },
                                color = if (room.isOccupied) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.error
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}