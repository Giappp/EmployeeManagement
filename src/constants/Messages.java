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
        public static final String MIN_SALARY = "Lương nhỏ nhất";
        public static final String MAX_SALARY = "Lương Lớn nhất";
        public static final String PARTIAL_UPDATE = "lưu thông tin và thoát";
        public static final String UPDATE_ALL = "Cập nhật tất cả";
        public static final String CANCEL = "Hủy";

        public static final String SEARCH_NAME = "Tìm kiếm theo tên";
        public static final String SEARCH_DIVISION = "Tìm kiếm theo phòng ban";
        public static final String SEARCH_STATUS = "Tìm kiếm theo trạng thái";
        public static final String SEARCH_SALARY_RANGE = "Tìm kiếm theo khoảng lương";
        public static final String SEARCH_EXECUTE = "Bắt đầu tìm kiếm";
        public static final String SEARCH_CANCEL = "Thoát tìm kiếm";

        public static final String SORT_SALARY = "Sắp xếp theo lương";
        public static final String SORT_NAME = "Sắp xếp theo tên (alphabet)";
        public static final String SORT_JOIN_DATE = "Sắp xếp ngày vào làm";

        public static final String STATS_TOTAL_EMPLOYEE = "Số Lượng nhân viên";
        public static final String STATS_TOTAL_SALARY = "Tổng quỹ lương";
        public static final String STATS_AVG_SALARY = "Lương trung bình";
        public static final String STATS_MAX_SALARY = "Lương lớn nhất";
        public static final String STATS_TOP_3 = "TOP 3 người lương cao nhất";
        public static final String STATS_GROUP_BY_DIVISION = "Số lượng người theo phòng ban";
        public static final String STATS_COUNT_ACTIVE = "Đếm số nhân viên đang hoạt động";
    }

    public static final class Success {
        public static final String INSERT = "Thêm mới thành công";
        public static final String UPDATE = "Cập nhật thành công";
        public static final String REMOVE = "Xóa nhân viên thành công";
        public static final String EMPTY = "Dach sách nhân viên đang rỗng";
        public static final String SEARCH_EMPTY = "Không tìm thấy nhân viên với tiêu chí trên";
        public static final String PERSIST = "Save success";
    }

    public static final class Error {
        public static final String INVALID_EMAIL = "Email không hợp lệ";
        public static final String INVALID_PHONE = "Số điện thoại không hợp lệ";
        public static final String INVALID_NAME = "Tên không được để trống";
        public static final String INVALID_DIVISION = "Phòng ban không được để trống";
        public static final String INVALID_CHOICE = "Lựa chọn không hợp lệ";
        public static final String INVALID_VALUE = "Giá trị không hợp lệ";
        public static final String INVALID_NUMBER = "Số không hợp lệ";
        public static final String INVALID_DATE = "Định dạng ngày không hợp lệ";
    }

    public static final class DEFAULT {
        public static final String SEARCH = "Bắt đầu tìm kiếm với bộ lọc";
        public static final String CANCEL = "Đã hủy hành động";
        public static final String CANCEL_AND_SAVE = "Lưu thay đổi thành công";
    }
}
