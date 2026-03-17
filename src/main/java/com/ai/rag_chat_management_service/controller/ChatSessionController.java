package com.ai.rag_chat_management_service.controller;


import com.ai.rag_chat_management_service.dto.CreateSessionRequestBody;
import com.ai.rag_chat_management_service.dto.UserSessionResponseBody;
import com.ai.rag_chat_management_service.service.ChatSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Chat Sessions", description = "APIs for managing chat sessions")
@RestController
@RequestMapping("/api/sessions")
public class ChatSessionController {

    private final ChatSessionService chatSessionService;

    public ChatSessionController(ChatSessionService chatSessionService) {
        this.chatSessionService = chatSessionService;
    }


    @Operation(summary = "Create a new chat session")
    @PostMapping
    public ResponseEntity<Void> createSession(CreateSessionRequestBody createSessionRequestBody) {
        chatSessionService.createSession(createSessionRequestBody);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a session")
    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> deleteSession(@PathVariable UUID sessionId) {
        chatSessionService.deleteSession(sessionId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all sessions for a user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserSessionResponseBody>> getUserSessions(@PathVariable UUID userId) {
        return ResponseEntity.ok(chatSessionService.getUserSessions(userId));
    }

    @Operation(summary = "Rename session")
    @PatchMapping("/{sessionId}/rename")
    public ResponseEntity<Void> renameSession(@PathVariable UUID sessionId, @RequestParam(name = "name") String name) {
        chatSessionService.renameSession(sessionId, name);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Toggle session")
    @PatchMapping("/{sessionId}/toggle")
    public ResponseEntity<Void> toggleFavourite(@PathVariable UUID sessionId) {
        chatSessionService.toggleFavorite(sessionId);
        return ResponseEntity.noContent().build();
    }
}