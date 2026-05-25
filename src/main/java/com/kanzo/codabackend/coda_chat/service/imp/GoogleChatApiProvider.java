package com.kanzo.codabackend.coda_chat.service.imp;

import java.util.Map;

import org.checkerframework.checker.units.qual.t;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.kanzo.codabackend.coda_chat.config.GoogleChatCredentialsProvider;
import com.kanzo.codabackend.coda_chat.dto.ChatMessageRequest;
import com.kanzo.codabackend.coda_chat.dto.ChatMessageResponse;
import com.kanzo.codabackend.coda_chat.service.IChatProvider;

@Service
@ConditionalOnProperty(name = "chat.provider", havingValue = "google-api")
public class GoogleChatApiProvider implements IChatProvider {

    private final RestClient restClient;
    private final GoogleChatCredentialsProvider credentialsProvider;
    private final String baseUrl;

    public GoogleChatApiProvider(
            GoogleChatCredentialsProvider credentialsProvider,
            @Value("${google.chat.base-url}") String baseUrl) {
        this.credentialsProvider = credentialsProvider;
        this.baseUrl = baseUrl;
        this.restClient = RestClient.builder().build();
    }

    @Override
    public ChatMessageResponse send(ChatMessageRequest request) {
        String accessToken = credentialsProvider.getAccessToken();

        @SuppressWarnings("unchecked")
        Map<String, Object> response = restClient.post()
                .uri(baseUrl + "/" + request.space() + "/messages")
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(Map.of("text", request.message()))
                .retrieve()
                .body(Map.class);

        String googleMessageName = response != null
                ? (String) response.get("name")
                : null;

        return new ChatMessageResponse(
                true,
                request.space(),
                request.message(),
                request.senderEmail(),
                request.taskId());
    }

}
