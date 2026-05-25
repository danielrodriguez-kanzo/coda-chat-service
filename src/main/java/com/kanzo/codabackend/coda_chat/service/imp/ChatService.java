package com.kanzo.codabackend.coda_chat.service.imp;

import org.springframework.stereotype.Service;

import com.kanzo.codabackend.coda_chat.dto.ChatMessageRequest;
import com.kanzo.codabackend.coda_chat.dto.ChatMessageResponse;
import com.kanzo.codabackend.coda_chat.service.IChatProvider;
import com.kanzo.codabackend.coda_chat.service.IChatService;

@Service
public class ChatService implements IChatService {

    private final IChatProvider chatProvider;

    public ChatService(IChatProvider chatProvider) {
        this.chatProvider = chatProvider;
    }

    @Override
    public ChatMessageResponse sendMessage(ChatMessageRequest request) {
        return chatProvider.send(request);
    }

}
