package com.kanzo.codabackend.coda_chat.service.imp;

import org.springframework.stereotype.Service;

import com.kanzo.codabackend.coda_chat.dto.DirectMessageLinkResponse;
import com.kanzo.codabackend.coda_chat.service.IDirectMessageProvider;

@Service
public class DirectMessageService {

    private final IDirectMessageProvider directMessageProvider;

    public DirectMessageService(IDirectMessageProvider directMessageProvider) {
        this.directMessageProvider = directMessageProvider;
    }

    public DirectMessageLinkResponse findDirectMessageLink(String userEmail) {
        return directMessageProvider.findDirectMessageLink(userEmail);
    }
}
