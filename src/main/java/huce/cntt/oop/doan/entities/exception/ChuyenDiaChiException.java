package huce.cntt.oop.doan.entities.exception;

public class ChuyenDiaChiException extends Exception {
    private String message;

    public ChuyenDiaChiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
