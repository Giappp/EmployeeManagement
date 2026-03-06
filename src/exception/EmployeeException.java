package exception;

import enums.ErrorCode;

public class EmployeeException extends RuntimeException {
    public String message;
    public String errorCode;

    public EmployeeException(ErrorCode errorCode) {
        this.message = errorCode.message;
        this.errorCode = errorCode.code;
    }
}
