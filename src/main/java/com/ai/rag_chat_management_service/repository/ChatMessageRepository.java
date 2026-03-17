package com.ai.rag_chat_management_service.repository;

import com.ai.rag_chat_management_service.entity.ChatMessage;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {
    @Query("SELECT m FROM ChatMessage m WHERE m.session.id = :sessionId ORDER BY m.createdAt ASC")
    Page<ChatMessage> findBySessionOrderByCreatedAtAsc(@Param("sessionId") UUID sessionId,
                                              Pageable pageable);

    @Modifying
    @Transactional
    void deleteBySession_Id(UUID sessionId);

}