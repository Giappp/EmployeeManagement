package exception;

import enums.ErrorCode;

public class ResourceNotFoundException extends RuntimeException {
    public String message;
    public String errorCode;

    public ResourceNotFoundException(ErrorCode errorCode) {
        this.message = errorCode.message;
        this.errorCode = errorCode.code;
    }
}
