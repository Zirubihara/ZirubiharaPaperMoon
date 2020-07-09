package com.zirubihara.phototraveller.phototraveller.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {super("nie znaleziono postu " +id);}
}
