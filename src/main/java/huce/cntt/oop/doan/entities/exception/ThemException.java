package huce.cntt.oop.doan.entities.exception;

public class ThemException extends Exception {
    private String message;

    public ThemException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
