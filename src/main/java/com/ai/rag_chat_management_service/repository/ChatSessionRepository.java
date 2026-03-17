package com.ai.rag_chat_management_service.repository;

import com.ai.rag_chat_management_service.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ChatSessionRepository extends JpaRepository<ChatSession, UUID> {
    List<ChatSession> findByUserId(UUID userId);
}
