package br.com.www.application.exception;
import br.com.www.support.exception.SystemException;

public class PermissionException extends SystemException {

    public PermissionException() {
        super();
    }

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(Throwable cause) {
        super(cause);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
