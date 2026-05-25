package com.kanzo.codabackend.coda_chat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ChatMessageRequest(
                @NotBlank String space,
                @NotBlank String message,
                @Email String senderEmail,
                String taskId) {

}
