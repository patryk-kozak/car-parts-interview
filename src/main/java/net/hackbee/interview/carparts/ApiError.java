package net.hackbee.interview.carparts;

import org.springframework.http.HttpStatus;

public class ApiError {
    private final HttpStatus status;
    private final String reason;

    public ApiError(HttpStatus status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
}