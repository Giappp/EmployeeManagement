package enums;

public enum ErrorCode {
    EMPLOYEE_ID_NOT_FOUND("Không tìm thấy Id nhân viên", "1000");
    public final String message;
    public final String code;

    ErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
