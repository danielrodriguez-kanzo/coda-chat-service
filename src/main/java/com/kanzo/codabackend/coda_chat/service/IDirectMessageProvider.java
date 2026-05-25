package com.kanzo.codabackend.coda_chat.service;

import com.kanzo.codabackend.coda_chat.dto.DirectMessageLinkResponse;

public interface IDirectMessageProvider {

    DirectMessageLinkResponse findDirectMessageLink(String userEmail);

}
