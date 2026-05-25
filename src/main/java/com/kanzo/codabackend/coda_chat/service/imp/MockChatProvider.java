package com.kanzo.codabackend.coda_chat.service.imp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.kanzo.codabackend.coda_chat.dto.ChatMessageRequest;
import com.kanzo.codabackend.coda_chat.dto.ChatMessageResponse;
import com.kanzo.codabackend.coda_chat.service.IChatProvider;

@Service
@ConditionalOnProperty(name = "chat.provider", havingValue = "mock", matchIfMissing = true)
public class MockChatProvider implements IChatProvider {

    @Override
    public ChatMessageResponse send(ChatMessageRequest request) {
        System.out.println("USANDO MOCK PROVIDER");

        return new ChatMessageResponse(
                true,
                request.space(),
                request.message(),
                request.senderEmail(),
                request.taskId());
    }

}
