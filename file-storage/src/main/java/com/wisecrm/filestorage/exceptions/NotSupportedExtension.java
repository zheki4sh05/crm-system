package com.wisecrm.filestorage.exceptions;

public class NotSupportedExtension extends Exception{
    public NotSupportedExtension(String message) {
        super(message);
    }

    public NotSupportedExtension() {
        super("Not supported extension");
    }
}
