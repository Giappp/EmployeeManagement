package constants;

public class Messages {
    private Messages() {
    }

    public static final class Prompt {
        public static final String CHOICE = "Lựa chọn của bạn: ";
        public static final String ID = "Id: ";
        public static final String ID_UPDATE = "Id nhân viên cần sửa: ";
        public static final String ID_REMOVE = "Id nhân viên cần xóa: ";
        public static final String NAME = "Họ Tên: ";
        public static final String EMAIL = "Email: ";
        public static final String PHONE = "Phone: ";
        public static final String STATUS = "Trạng thái nhân viên: ";
        public static final String SALARY = "Lương: ";
        public static final String DIVISION = "Phòng ban: ";
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
    }
}
