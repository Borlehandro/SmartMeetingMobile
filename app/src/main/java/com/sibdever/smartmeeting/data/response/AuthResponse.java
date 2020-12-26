package com.sibdever.smartmeeting.data.response;

import com.sibdever.smartmeeting.data.ResponseStatus;

public class AuthResponse {
    private final ResponseStatus responseStatus;

    public AuthResponse(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }
}
