package com.thedickensdev.util;

public class MatrixCalculationException extends Exception {
    private String massage = null;
    public MatrixCalculationException(String message) {
        this.massage = massage;

    }

    @Override
    public String getMessage() {
        return massage;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.err.println(massage);
    }
}
