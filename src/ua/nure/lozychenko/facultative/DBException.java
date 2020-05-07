package ua.nure.lozychenko.facultative;

public class DBException extends Exception {
    public DBException(String message) {
        super(message);
    }

    public DBException(Throwable cause) {
        super(cause);
    }
}
