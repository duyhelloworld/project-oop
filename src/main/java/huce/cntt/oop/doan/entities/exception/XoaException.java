package huce.cntt.oop.doan.entities.exception;

public class XoaException extends Exception {
    private String message;

    public XoaException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
