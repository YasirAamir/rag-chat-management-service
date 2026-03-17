package com.ai.rag_chat_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageRequestBody {
    private String sender;
    private String content;
    private String context;
}
