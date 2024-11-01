package com.safaricom.microservices.split.payment.service.spring_boot_file_upload.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }
}
