package com.showtime.corelib.constant.value;

/**
 * @author Vengatesan Nagarajan
 */
public enum ValueConstants {

    SAMPLE("");

    public static final Boolean TRUE = Boolean.TRUE;
    public static final Boolean FALSE = Boolean.FALSE;

    private String value;

    ValueConstants(String valueName) {
        this.value = valueName;
    }

    public String getValue() {
        return value;
    }
}
