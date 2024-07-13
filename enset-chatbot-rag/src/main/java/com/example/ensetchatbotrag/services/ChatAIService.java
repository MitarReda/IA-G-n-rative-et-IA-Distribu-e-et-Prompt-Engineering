package com.example.ensetchatbotrag.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SimpleVectorStore;

import java.util.List;
//import org.springframework.stereotype.Service;

@BrowserCallable
@AnonymousAllowed
public class ChatAIService {

    private ChatClient chatClient;
    private SimpleVectorStore simpleVectorStore;
    public ChatAIService(ChatClient.Builder builder,SimpleVectorStore simpleVectorStore){
        this.chatClient=builder.build();
        this.simpleVectorStore=simpleVectorStore;
    }

    public String ragChat(String question){
        List<Document> doc= simpleVectorStore.similaritySearch(question);
        List<String> context=doc.stream().map(Document::getContent).toList();
        return chatClient.prompt().user(question).call().content();
    }
}
