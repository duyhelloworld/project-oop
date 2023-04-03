package com.helloworld.entities.properties;

public class WrongFormatException extends Exception {
    public WrongFormatException(String msg) {
        super(msg);
    }

    @Override
    public void printStackTrace() {
        System.out.println("Wrong data format exception : " + getMessage());
    }
}
