package huce.cntt.oop.doan.entities.exception;

public class CapNhatException extends Exception {
    private String message;

    public CapNhatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
