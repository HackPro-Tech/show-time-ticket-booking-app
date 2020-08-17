package com.showtime.authserver.kafka.message;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Data
@Builder
public class RegisterNewUserMessage {
    private UUID userId;
    private String email;
    private String phoneNo;
}
