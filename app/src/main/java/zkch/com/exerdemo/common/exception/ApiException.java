package zkch.com.exerdemo.common.exception;

public class ApiException extends BaseException {
    public ApiException(int code, String displayMsg) {
        super(code, displayMsg);
    }
}
