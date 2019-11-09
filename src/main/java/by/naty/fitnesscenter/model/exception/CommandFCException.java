package by.naty.fitnesscenter.model.exception;

public class CommandFCException extends Exception {

    public CommandFCException() {
    }

    public CommandFCException(String message) {
        super(message);
    }

    public CommandFCException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandFCException(Throwable cause) {
        super(cause);
    }
}
