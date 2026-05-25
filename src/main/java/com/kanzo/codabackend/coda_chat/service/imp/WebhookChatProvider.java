package com.kanzo.codabackend.coda_chat.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.kanzo.codabackend.coda_chat.dto.ChatMessageRequest;
import com.kanzo.codabackend.coda_chat.dto.ChatMessageResponse;
import com.kanzo.codabackend.coda_chat.service.IChatProvider;

@Service
@ConditionalOnProperty(name = "chat.provider", havingValue = "webhook")
public class WebhookChatProvider implements IChatProvider {

    private final RestClient restClient;
    private final String webhookUrl;

    public WebhookChatProvider(
            @Value("${chat.webhook-url}") String webhookUrl) {

        this.restClient = RestClient.builder().build();
        this.webhookUrl = webhookUrl;
    }

    @Override
    public ChatMessageResponse send(ChatMessageRequest request) {
        restClient.post()
                .uri(webhookUrl)
                .header("Content-Type", "application/json")
                .body(Map.of(
                        "text",
                        request.message()))
                .retrieve()
                .toBodilessEntity();

        System.out.println("USANDO WEBHOOK PROVIDER");
        return new ChatMessageResponse(
                true,
                request.space(),
                request.message(),
                request.senderEmail(),
                request.taskId());
    }

}
