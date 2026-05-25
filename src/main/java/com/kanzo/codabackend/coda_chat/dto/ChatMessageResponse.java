package com.kanzo.codabackend.coda_chat.dto;

public record ChatMessageResponse(
        boolean sent,
        String space,
        String message,
        String senderEmail,
        String taskId) {

}
