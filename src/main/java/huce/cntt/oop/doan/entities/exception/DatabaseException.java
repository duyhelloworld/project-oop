package huce.cntt.oop.doan.entities.exception;

public class DatabaseException extends Exception {
    private String message;

    public DatabaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
