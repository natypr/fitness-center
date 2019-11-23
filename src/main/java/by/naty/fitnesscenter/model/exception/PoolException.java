package by.naty.fitnesscenter.model.exception;

public class PoolException extends Exception{

    public PoolException() {
    }

    public PoolException(String message) {
        super(message);
    }

    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolException(Throwable cause) {
        super(cause);
    }
}