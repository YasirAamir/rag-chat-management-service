package com.ai.rag_chat_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionMessageResponseBody {
    private UUID id;
    private String content;
    private String context;
    private String sender;
    private Long createdAt;
}
