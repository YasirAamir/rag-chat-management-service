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
public class UserSessionResponseBody {
    private UUID id;
    private String name;
    private UUID userId;
    private Long createdAt;
    private Long updatedAt;
    private Boolean isFavourite; 
}