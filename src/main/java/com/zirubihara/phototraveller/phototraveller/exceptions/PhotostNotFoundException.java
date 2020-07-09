package com.zirubihara.phototraveller.phototraveller.exceptions;

public class PhotostNotFoundException extends RuntimeException {
    public PhotostNotFoundException(Long id) {
        super("Nie znaleziono Photos " + id);
    }
}
