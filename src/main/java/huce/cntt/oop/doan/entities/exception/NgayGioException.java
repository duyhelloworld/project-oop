package huce.cntt.oop.doan.entities.exception;

public class NgayGioException extends Exception {
    private String message;

    public NgayGioException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
