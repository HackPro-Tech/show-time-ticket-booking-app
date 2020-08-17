package com.showtime.exception;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */
public class MaxRecordLimitException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MaxRecordLimitException(String message) {
		super(message);
	}

}
