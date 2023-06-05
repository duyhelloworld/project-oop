package huce.cntt.oop.doan.entities.exception;

public class EmailException extends Exception {
    private String message;

    public EmailException(){
        message = "Email sai định dạng\nHãy viết lại với kí tự '@'!";
    }

    public EmailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
