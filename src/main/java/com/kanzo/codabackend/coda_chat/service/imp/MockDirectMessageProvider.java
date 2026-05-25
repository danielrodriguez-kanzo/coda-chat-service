package com.kanzo.codabackend.coda_chat.service.imp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.kanzo.codabackend.coda_chat.dto.DirectMessageLinkResponse;
import com.kanzo.codabackend.coda_chat.service.IDirectMessageProvider;

@Service
@ConditionalOnProperty(name = "chat.dm.provider", havingValue = "mock", matchIfMissing = true)
public class MockDirectMessageProvider implements IDirectMessageProvider {

    @Override
    public DirectMessageLinkResponse findDirectMessageLink(String userEmail) {
        return new DirectMessageLinkResponse(
                true,
                userEmail,
                "spaces/mock-dm-space",
                "https://chat.google.com/dm/mock-dm-space",
                "Mock DM link generated");
    }

}
