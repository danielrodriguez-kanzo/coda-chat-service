package com.kanzo.codabackend.coda_chat.service.imp;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.kanzo.codabackend.coda_chat.config.GoogleChatCredentialsProvider;
import com.kanzo.codabackend.coda_chat.dto.DirectMessageLinkResponse;
import com.kanzo.codabackend.coda_chat.service.IDirectMessageProvider;

@Service
@ConditionalOnProperty(name = "chat.dm.provider", havingValue = "google-api")
public class GoogleChatDirectMessageProvider implements IDirectMessageProvider {

    private final RestClient restClient;
    private final GoogleChatCredentialsProvider credentialsProvider;
    private final String baseUrl;

    public GoogleChatDirectMessageProvider(
            GoogleChatCredentialsProvider credentialsProvider,
            @Value("${google.chat.base-url}") String baseUrl) {
        this.credentialsProvider = credentialsProvider;
        this.baseUrl = baseUrl;
        this.restClient = RestClient.builder().build();
    }

    @Override
    public DirectMessageLinkResponse findDirectMessageLink(String userEmail) {
        String accessToken = credentialsProvider.getAccessToken();

        String userName = "users/" + userEmail;
        String encodedName = URLEncoder.encode(userName, StandardCharsets.UTF_8);

        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> response = restClient.get()
                    .uri(baseUrl + "/spaces:findDirectMessage?name=" + encodedName)
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .body(Map.class);

            String spaceName = response != null
                    ? (String) response.get("name")
                    : null;

            return new DirectMessageLinkResponse(
                    spaceName != null,
                    userEmail,
                    spaceName,
                    buildChatUrl(spaceName),
                    "Direct message found");

        } catch (HttpClientErrorException.NotFound e) {
            return new DirectMessageLinkResponse(
                    false,
                    userEmail,
                    null,
                    null,
                    "No existing direct message found");
        }
    }

    private String buildChatUrl(String spaceName) {
        if (spaceName == null || !spaceName.startsWith("spaces/")) {
            return null;
        }

        String spaceId = spaceName.replace("spaces/", "");
        return "https://chat.google.com/dm/" + spaceId;
    }
}
