package com.sibdever.smartmeeting.data.request;

public class AuthRequest {
    private String email;
    private String cookie;

    public AuthRequest(String email, String cookie) {
        this.email = email;
        this.cookie = cookie;
    }

    public String getEmail() {
        return email;
    }

    public String getCookie() {
        return cookie;
    }
}
