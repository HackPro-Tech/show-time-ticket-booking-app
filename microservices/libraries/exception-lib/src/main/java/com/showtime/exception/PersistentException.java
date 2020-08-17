package com.showtime.exception;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */
public class PersistentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersistentException(String message) {
		super(message);
	}

}
