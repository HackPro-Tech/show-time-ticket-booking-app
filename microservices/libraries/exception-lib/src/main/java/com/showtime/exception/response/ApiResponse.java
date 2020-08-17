package com.showtime.exception.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */
@Data
@Builder
public class ApiResponse {

	private LocalDateTime timeStamp;
	private String error;
	private String message;
	private String owner;
	private String path;
	private int status;
}