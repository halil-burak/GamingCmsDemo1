package com.turkcell.playcell.cms.cmsdemo1.util;

public class UserNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super("Could not find user with " + id);
    }
}
