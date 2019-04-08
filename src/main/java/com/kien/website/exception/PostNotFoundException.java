package com.kien.website.exception;

public class PostNotFoundException extends Exception{
    public PostNotFoundException(String url) {
        super(url);
    }
}
