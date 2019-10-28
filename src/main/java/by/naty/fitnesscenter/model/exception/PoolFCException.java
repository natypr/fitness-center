package by.naty.fitnesscenter.model.exception;

public class PoolFCException extends Exception{

    public PoolFCException() {
    }

    public PoolFCException(String message) {
        super(message);
    }

    public PoolFCException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolFCException(Throwable cause) {
        super(cause);
    }
}