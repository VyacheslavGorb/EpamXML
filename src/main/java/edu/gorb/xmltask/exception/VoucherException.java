package edu.gorb.xmltask.exception;

public class VoucherException extends Exception {
    public VoucherException() {
    }

    public VoucherException(String message) {
        super(message);
    }

    public VoucherException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoucherException(Throwable cause) {
        super(cause);
    }
}
