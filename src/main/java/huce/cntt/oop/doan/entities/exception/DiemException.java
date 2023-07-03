package huce.cntt.oop.doan.entities.exception;

public class DiemException extends Exception {
    private String message;

    public DiemException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
