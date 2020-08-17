package com.showtime.exception.constant;

/**
 * @author Vengatesan Nagarajan
 */
public enum ApiMessage {

    OWNER("com.hackpro"),
    CONFLICT_ERROR("Conflict"),
    SERVER_ERROR("Internal Server"),
    UNAUTHORIZED("UnAuthorized"),
    FORBIDDEN("Access denied");

    private final String value;

    public String value() {
        return this.value;
    }

    ApiMessage(String value) {
        this.value = value;
    }
}
