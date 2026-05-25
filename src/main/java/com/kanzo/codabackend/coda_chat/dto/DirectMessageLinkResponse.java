package com.kanzo.codabackend.coda_chat.dto;

public record DirectMessageLinkResponse(
        boolean found,
        String userEmail,
        String spaceName,
        String url,
        String message) {}
