package com.ai.rag_chat_management_service.service;

import com.ai.rag_chat_management_service.dto.CreateSessionRequestBody;
import com.ai.rag_chat_management_service.dto.UserSessionResponseBody;
import com.ai.rag_chat_management_service.entity.ChatSession;
import com.ai.rag_chat_management_service.exception.ResourceNotFoundException;
import com.ai.rag_chat_management_service.repository.ChatMessageRepository;
import com.ai.rag_chat_management_service.repository.ChatSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatSessionService {

    private final ChatSessionRepository chatSessionRepository;
    private final ChatMessageRepository chatMessageRepository;

    public void createSession(CreateSessionRequestBody createSessionRequestBody) {
        ChatSession session = ChatSession.builder()
                .userId(createSessionRequestBody.getUserId())
                .name(createSessionRequestBody.getName())
                .favourite(false)
                .build();
        chatSessionRepository.save(session);
    }

    public List<UserSessionResponseBody> getUserSessions(UUID userId) {
        return chatSessionRepository.findByUserId(userId)
                .stream().map(obj -> {
                    UserSessionResponseBody userSession = new UserSessionResponseBody();
                    userSession.setId(obj.getId());
                    userSession.setName(obj.getName());
                    userSession.setUserId(obj.getUserId());
                    userSession.setCreatedAt(obj.getCreatedAt());
                    userSession.setUpdatedAt(obj.getUpdatedAt());
                    userSession.setIsFavourite(obj.isFavourite());
                    return userSession;
                }).toList();
    }

    public void renameSession(UUID id, String name) {
        ChatSession session = chatSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found"));
        session.setName(name);
        chatSessionRepository.save(session);
    }

    public void toggleFavorite(UUID id) {
        ChatSession session = chatSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found"));
        session.setFavourite(!session.isFavourite());
        chatSessionRepository.save(session);
    }

    public void deleteSession(UUID id) {
        chatMessageRepository.deleteBySession_Id(id);
        chatSessionRepository.deleteById(id);
    }

}