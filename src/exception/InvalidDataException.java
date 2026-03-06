package exception;

import enums.ErrorCode;

public class InvalidDataException extends RuntimeException {
    public String message;
    public String errorCode;

    public InvalidDataException(ErrorCode errorCode) {
        this.message = errorCode.message;
        this.errorCode = errorCode.code;
    }
}
