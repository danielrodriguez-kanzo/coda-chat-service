package com.kanzo.codabackend.coda_chat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanzo.codabackend.coda_chat.dto.ChatMessageRequest;
import com.kanzo.codabackend.coda_chat.dto.ChatMessageResponse;
import com.kanzo.codabackend.coda_chat.service.IChatService;
import com.kanzo.codabackend.coda_chat.service.imp.ChatService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final IChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/message")
    public ChatMessageResponse sendMessage(@Valid @RequestBody ChatMessageRequest request) {
        return chatService.sendMessage(request);
    }

}
