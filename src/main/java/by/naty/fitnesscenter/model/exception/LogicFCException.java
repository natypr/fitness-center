package by.naty.fitnesscenter.model.exception;

public class LogicFCException extends Exception {

    public LogicFCException() {
    }

    public LogicFCException(String message) {
        super(message);
    }

    public LogicFCException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicFCException(Throwable cause) {
        super(cause);
    }
}
