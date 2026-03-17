package com.ai.rag_chat_management_service.service;

import com.ai.rag_chat_management_service.dto.CreateMessageRequestBody;
import com.ai.rag_chat_management_service.dto.SessionMessageResponseBody;
import com.ai.rag_chat_management_service.entity.ChatMessage;
import com.ai.rag_chat_management_service.exception.ResourceNotFoundException;
import com.ai.rag_chat_management_service.repository.ChatMessageRepository;
import com.ai.rag_chat_management_service.repository.ChatSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    private final ChatSessionRepository chatSessionRepository;

    public void addMessage(UUID sessionId, CreateMessageRequestBody createMessageRequestBody) {
        chatSessionRepository.findById(sessionId)
                .ifPresentOrElse(session -> {
                    ChatMessage message = ChatMessage.builder()
                            .session(session)
                            .sender(createMessageRequestBody.getSender())
                            .content(createMessageRequestBody.getContent())
                            .context(createMessageRequestBody.getContext())
                            .build();

                    chatMessageRepository.save(message);
                }, () -> {
                    throw new ResourceNotFoundException("ChatSession", "id", sessionId);
                });
    }
    

    public List<SessionMessageResponseBody> getChatMessages(UUID sessionId, int page, int size) {
        return chatMessageRepository.findBySessionOrderByCreatedAtAsc(
                sessionId,
                PageRequest.of(page, size, Sort.by("createdAt"))
        ).getContent().stream().map(obj -> {
            SessionMessageResponseBody sessionMessage = new SessionMessageResponseBody();
           sessionMessage.setId(obj.getId());
           sessionMessage.setSender(obj.getSender());
           sessionMessage.setContent(obj.getContent());
           sessionMessage.setContext(obj.getContext());
           sessionMessage.setCreatedAt(obj.getCreatedAt());
           return sessionMessage;
        }).toList();
    }
}