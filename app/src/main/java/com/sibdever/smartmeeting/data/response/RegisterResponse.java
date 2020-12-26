package com.sibdever.smartmeeting.data.response;

import com.sibdever.smartmeeting.data.ResponseStatus;

import java.net.HttpCookie;

public class RegisterResponse {
    private final ResponseStatus responseStatus;
    private final HttpCookie cookie;

    public RegisterResponse(ResponseStatus responseStatus, HttpCookie cookie) {
        this.responseStatus = responseStatus;
        this.cookie = cookie;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public HttpCookie getCookie() {
        return cookie;
    }
}
