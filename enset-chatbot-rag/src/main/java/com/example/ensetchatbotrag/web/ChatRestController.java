package com.example.ensetchatbotrag.web;

import com.example.ensetchatbotrag.services.ChatAIService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/chat")

public class ChatRestController {
    private ChatAIService chatAIService;
    public ChatRestController(ChatAIService chatAIService){
        this.chatAIService=chatAIService;
    }

    @GetMapping(value = "/ask",produces = MediaType.TEXT_PLAIN_VALUE)
    public String Ask(String question){
        return chatAIService.ragChat(question);
    }
}
