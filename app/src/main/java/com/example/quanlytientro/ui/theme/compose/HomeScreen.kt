package com.example.quanlytientro.ui.theme.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlytientro.data.RoomEntity

// 1. UI State cho Lab 5
sealed interface RentalUiState {
    data object Loading : RentalUiState
    data class Success(val roomList: List<RoomEntity>) : RentalUiState
    data class Error(val message: String) : RentalUiState
}

// Màu xanh ngọc Teal đặc trưng của Lab mẫu
val TealPrimary = Color(0xFF00897B)
val TealBackground = Color(0xFFE0F2F1)

@Composable
fun MainHomeScreen(
    uiState: RentalUiState,
    onRetry: () -> Unit
) {
    var currentScreen by remember { mutableIntStateOf(0) } // 0: Home, 1: Chi tiết, 2: DarkMode/Theme
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedRoom by remember { mutableStateOf<RoomEntity?>(null) }

    val colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme(primary = TealPrimary)

    MaterialTheme(colorScheme = colorScheme) {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (currentScreen) {
                0 -> {
                    when (uiState) {
                        is RentalUiState.Loading -> LoadingContent()
                        is RentalUiState.Success -> HomeScreenContent(
                            rooms = uiState.roomList,
                            onRoomClick = { room ->
                                selectedRoom = room
                                currentScreen = 1
                            },
                            onOpenTheme = { currentScreen = 2 }
                        )
                        is RentalUiState.Error -> ErrorContent(
                            message = uiState.message,
                            onRetry = onRetry
                        )
                    }
                }
                1 -> RoomDetailScreen(
                    room = selectedRoom,
                    onBack = { currentScreen = 0 }
                )
                2 -> ThemeSettingScreen(
                    isDarkMode = isDarkMode,
                    onDarkModeChange = { isDarkMode = it },
                    onBack = { currentScreen = 0 }
                )
            }
        }
    }
}

// Màn hình Loading
@Composable
fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = TealPrimary)
    }
}

// Màn hình Error (Lab 5)
@Composable
fun ErrorContent(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(48.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = message, color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry, colors = ButtonDefaults.buttonColors(containerColor = TealPrimary)) {
            Text("Tải lại / Thử lại")
        }
    }
}

// MÀN HÌNH TRANG CHỦ (Lab 3 List + Lab 4)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    rooms: List<RoomEntity>,
    onRoomClick: (RoomEntity) -> Unit,
    onOpenTheme: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quản Lý Tiền Trọ", color = Color.White, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = onOpenTheme) {
                        Icon(Icons.Default.Settings, contentDescription = "Theme", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = TealPrimary)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("Danh sách phòng trọ (Bấm xem chi tiết)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            items(rooms) { room ->
                Card(
                    onClick = { onRoomClick(room) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.size(48.dp).clip(CircleShape).background(TealBackground),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(room.roomNumber, fontWeight = FontWeight.Bold, color = TealPrimary)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Phòng ${room.roomNumber}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Giá: ${room.price.toLong()} VNĐ/tháng", style = MaterialTheme.typography.bodyMedium)
                        }
                        Text(
                            if (room.isOccupied) "Đã thuê" else "Còn trống",
                            color = if (room.isOccupied) Color.Red else TealPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

// MÀN HÌNH LAB 3 — CHI TIẾT PHÒNG
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomDetailScreen(room: RoomEntity?, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chi tiết phòng trọ", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = TealPrimary)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .background(TealBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = room?.roomNumber?.take(1) ?: "P",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = TealPrimary
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Phòng ${room?.roomNumber ?: "101"}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("Khu nhà trọ trung tâm", color = TealPrimary, fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(32.dp))

            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Home, contentDescription = null, tint = TealPrimary)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Giá thuê", style = MaterialTheme.typography.bodySmall)
                            Text("${room?.price?.toLong() ?: 1500000} VNĐ/tháng", fontWeight = FontWeight.Bold)
                        }
                    }
                    HorizontalDivider()
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = TealPrimary)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Trạng thái", style = MaterialTheme.typography.bodySmall)
                            Text(if (room?.isOccupied == true) "Đã có người ở" else "Đang phòng trống", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Call, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Gọi điện")
                }
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Email, contentDescription = null, tint = TealPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Gửi email", color = TealPrimary)
                }
            }
        }
    }
}

// MÀN HÌNH LAB 4 — THEME & DARK MODE
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSettingScreen(
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Giao diện & Theme", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = TealPrimary)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Chế độ tối (Dark Mode)", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Sử dụng giao diện tối để tiết kiệm pin và giảm mỏi mắt", style = MaterialTheme.typography.bodySmall)
                }
                Switch(checked = isDarkMode, onCheckedChange = onDarkModeChange)
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("XEM TRƯỚC THỀ", style = MaterialTheme.typography.labelMedium, color = TealPrimary)
            Spacer(modifier = Modifier.height(8.dp))

            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Tiêu đề thẻ", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Đây là nội dung mẫu của thẻ. Nó giúp bạn xem trước giao diện.", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = TealPrimary)
                    ) {
                        Text("HÀNH ĐỘNG")
                    }
                }
            }
        }
    }
}