 ĐỒ ÁN LẬP TRÌNH ANDROID KOTLIN - QUẢN LÝ TIỀN TRỌ
 THÔNG TIN SINH VIÊN
Họ và tên: Rcom Nho
Mã số sinh viên (MSSV): 231ctt013
Lớp:DHC23CTT01
Giảng viên hướng dẫn: ThS. Nguyễn Quốc Dũng

DANH SÁCH BÀI LAB ĐÃ HOÀN THÀNH

 Bài 1: Nền tảng Giao diện XML & View Binding
Lab 1: Form XML + View Binding
    - Thiết kế Form nhập liệu thông tin cá nhân/khách thuê.
    - Sử dụng `View Binding` để tương tác trực tiếp với các View thay vì `findViewById`.
Lab 2: Scaffold, LazyColumn & Tìm kiếm**
    - Hiển thị danh sách khách thuê/phòng trọ dạng danh sách cuộn mượt mà với `LazyColumn`.
    - Tích hợp ô tìm kiếm lọc dữ liệu động theo tên và thông tin.

 Bài 2: Giao diện Nâng cao & Jetpack Compose
Lab 3: Danh sách → Chi tiết & Điều hướng (35 điểm)**
    - Chuyển màn hình từ Danh sách sang màn hình Chi tiết phòng trọ/sinh viên (`RoomDetailScreen`).
    - Thiết kế giao diện Chi tiết chuẩn Material Design 3: Avatar tròn chữ cái cỡ lớn, các thông tin chi tiết và nút tác vụ (*Gọi điện*, *Gửi email*).
    - Tích hợp nút ** Back** điều hướng quay lại danh sách.

Lab 4: Theme Tokens & Dark Mode (30 điểm)**
    - Tự động chuyển đổi giao diện Sáng / Tối với **Switch Dark Mode**.
    - Áp dụng Bảng màu chuẩn Material 3 (`TealPrimary` / `MaterialTheme.colorScheme`), không hardcode màu.
    - Thiết kế màn hình Cài đặt Giao diện tích hợp thẻ **Xem Trước (Preview Card)**.

Lab 5: Quản lý Trạng thái UI State (25 điểm)**
    - Quản lý 3 trạng thái giao diện chuẩn bằng **`Sealed Interface`** (`RentalUiState`):
        - Loading: Hiển thị vòng xoay tải dữ liệu (`CircularProgressIndicator`).
        - Success: Hiển thị toàn bộ giao diện Trang chủ & Danh sách.
        - Error: Hiển thị thông báo lỗi kèm nút **Thử lại / Tải lại** hoạt động chuẩn xác.
    - Xử lý render UI theo luồng điều kiện **`when (uiState)`**.


CÔNG NGHỆ VÀ THƯ VIỆN SỬ DỤNG
Ngôn ngữ: Kotlin
UI Toolkit: Jetpack Compose (Material 3)
Cơ sở dữ liệu: Room Database (Entity, DAO, Repository)
Kiến trúc: MVVM / Unidirectional Data Flow (State Management)
SDK: Compile SDK 37 / Min SDK 24