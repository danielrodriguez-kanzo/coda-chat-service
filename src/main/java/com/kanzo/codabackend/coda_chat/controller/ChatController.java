package com.kanzo.codabackend.coda_chat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanzo.codabackend.coda_chat.dto.ChatMessageRequest;
import com.kanzo.codabackend.coda_chat.dto.ChatMessageResponse;
import com.kanzo.codabackend.coda_chat.dto.DirectMessageLinkResponse;
import com.kanzo.codabackend.coda_chat.service.IChatService;
import com.kanzo.codabackend.coda_chat.service.IDirectMessageProvider;
import com.kanzo.codabackend.coda_chat.service.imp.ChatService;
import com.kanzo.codabackend.coda_chat.service.imp.DirectMessageService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    private final DirectMessageService dmService;

    public ChatController(ChatService chatService, DirectMessageService dmService) {
        this.chatService = chatService;
        this.dmService = dmService;
    }

    @PostMapping("/message")
    public ChatMessageResponse sendMessage(@Valid @RequestBody ChatMessageRequest request) {
        return chatService.sendMessage(request);
    }

    @GetMapping("/dm-link")
    public DirectMessageLinkResponse getMethodName(@RequestParam String userEmail) {
        return dmService.findDirectMessageLink(userEmail);
    }

}
