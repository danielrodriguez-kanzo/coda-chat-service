package com.kanzo.codabackend.coda_chat.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;

@Component
public class GoogleChatCredentialsProvider {

    private final String credentialsPath;
    private final String scope;

    public GoogleChatCredentialsProvider(
            @Value("${google.chat.credentials-path}") String credentialsPath,
            @Value("${google.chat.scope}") String scope) {
        this.credentialsPath = credentialsPath;
        this.scope = scope;
    }

    public String getAccessToken() {
        if (credentialsPath == null || credentialsPath.isBlank()) {
            throw new IllegalStateException("GOOGLE_CHAT_CREDENTIALS is not configured");
        }

        try (FileInputStream inputStream = new FileInputStream(credentialsPath)) {
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(inputStream)
                    .createScoped(List.of(scope));

            credentials.refreshIfExpired();

            return credentials.getAccessToken().getTokenValue();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load Google Chat credentials", e);
        }
    }
}
