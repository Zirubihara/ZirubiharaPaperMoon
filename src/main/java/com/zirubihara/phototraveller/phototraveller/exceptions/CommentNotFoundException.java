package com.zirubihara.phototraveller.phototraveller.exceptions;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super("Nie znaleziono komentarza o id: " + id);
    }
}
