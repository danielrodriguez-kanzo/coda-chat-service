package com.kanzo.codabackend.coda_chat.service;

import com.kanzo.codabackend.coda_chat.dto.ChatMessageRequest;
import com.kanzo.codabackend.coda_chat.dto.ChatMessageResponse;

public interface IChatService {

    ChatMessageResponse sendMessage(ChatMessageRequest request);

}
