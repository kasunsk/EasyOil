package com.oilseller.oilbrocker.platform.dto;

public class Context {

    private String username;

    public Context(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
