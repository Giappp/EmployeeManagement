package constants;

/*
    Class đặt các Message cho người dùng
 */
public class Messages {
    private Messages() {
    }

    public static final class Prompt {
        public static final String CHOICE = "Lựa chọn của bạn: ";
        public static final String ID = "Id nhân viên: ";
        public static final String ID_UPDATE = "Id nhân viên cần sửa: ";
        public static final String ID_REMOVE = "Id nhân viên cần xóa: ";
        public static final String NAME = "Họ tên nhân viên: ";
        public static final String NAME_UPDATE = "Nhập họ tên mới: ";
        public static final String EMAIL = "Email: ";
        public static final String EMAIL_UPDATE = "Email mới: ";
        public static final String PHONE = "SỐ điện thoại: ";
        public static final String PHONE_UPDATE = "Số điện thoại mới: ";
        public static final String STATUS = "Trạng thái nhân viên: ";
        public static final String STATUS_UPDATE = "Trạng thái mơi cho nhân viên: ";
        public static final String SALARY = "Lương: ";
        public static final String SALARY_UPDATE = "Lương mới cho nhân viên: ";
        public static final String DIVISION = "Phòng ban: ";
        public static final String DIVISION_UPDATE = "Phòng ban mới cho nhân viên: ";
        public static final String JOIN_DATE = "Ngày gia nhập (định dạng dd/mm/yyyy): ";
        public static final String JOIN_DATE_UPDATE = "Ngày gia nhập mới (định dạng dd/mm/yyyy): ";
    }

    public static final class Success {
        public static final String INSERT = "Thêm mới thành công";
        public static final String UPDATE = "Cập nhật thành công";
        public static final String REMOVE = "Xóa nhân viên thành công";
        public static final String EMPTY = "Dach sách nhân viên đang rỗng";
    }

    public static final class Error {
        public static final String INVALID_EMAIL = "Email không hợp lệ";
        public static final String INVALID_PHONE = "Số điện thoại không hợp lệ";
        public static final String INVALID_NAME = "Tên không được để trống";
        public static final String INVALID_DIVISION = "Phòng ban không được để trống";
        public static final String INVALID_CHOICE = "Lựa chọn không hợp lệ";
        public static final String INVALID_VALUE = "Giá trị không hợp lệ";
        public static final String INVALID_NUMBER = "Số không hợp lệ";
    }

    public static final class DEFAULT {
        public static final String CANCEL = "Đã hủy hành động";
        public static final String CANCEL_AND_SAVE = "Lưu thay đổi thành công";
    }
}
