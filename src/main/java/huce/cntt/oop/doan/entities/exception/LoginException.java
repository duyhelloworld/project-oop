package huce.cntt.oop.doan.entities.exception;

public class LoginException extends Exception {
    private String message;

    public LoginException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
