package com.ai.rag_chat_management_service.controller;


import com.ai.rag_chat_management_service.dto.CreateMessageRequestBody;
import com.ai.rag_chat_management_service.dto.SessionMessageResponseBody;
import com.ai.rag_chat_management_service.service.ChatMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Chat Messages", description = "APIs for managing chat messages")
@RestController
@RequestMapping("/api/message")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @Operation(summary = "Add a new message")
    @PostMapping("/session/{sessionId}")
    public ResponseEntity<Void> addMessage(@PathVariable UUID sessionId,
                                           @RequestBody CreateMessageRequestBody createMessageRequestBody) {
        chatMessageService.addMessage(sessionId, createMessageRequestBody);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all messages for a session")
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<SessionMessageResponseBody>>
    getChatMessagesForSession(@PathVariable UUID sessionId,
                              @RequestParam(name = "page", defaultValue = "0", required = false ) Integer page,
                              @RequestParam(name = "size", defaultValue = "5", required = false )Integer size) {
        return ResponseEntity.ok(chatMessageService.getChatMessages(sessionId, page, size));
    }


}