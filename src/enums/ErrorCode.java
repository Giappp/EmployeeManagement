package enums;

public enum ErrorCode {
    EMPLOYEE_NOT_FOUND("Không tìm thấy Id nhân viên", "1000"),
    DUPLICATE_EMPLOYEE("Trùng ID", "1001"),
    NEGATIVE_SALARY("Lương âm", "1002"),
    INVALID_NAME("Tên không đúng định dạng", "2000");

    public final String message;
    public final String code;

    ErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
