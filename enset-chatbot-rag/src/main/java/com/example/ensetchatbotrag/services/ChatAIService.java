package com.example.ensetchatbotrag.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;
import java.util.Map;
//import org.springframework.stereotype.Service;

@BrowserCallable
@AnonymousAllowed
public class ChatAIService {

    private ChatClient chatClient;
    private SimpleVectorStore simpleVectorStore;
    @Value("classpath:/prompts/prompt-template.st")
    private Resource promptResource;


    public ChatAIService(ChatClient.Builder builder,SimpleVectorStore simpleVectorStore){
        this.chatClient=builder.build();
        this.simpleVectorStore=simpleVectorStore;

    }

    public String ragChat(String question){
        List<Document> doc= simpleVectorStore.similaritySearch(question);
        List<String> context=doc.stream().map(Document::getContent).toList();
        PromptTemplate promptTemplate=new PromptTemplate(promptResource);
        Prompt prom= promptTemplate.create(Map.of("context",context,"question",question));
        return chatClient.prompt(prom)
                //.user(question)
        .call().content();
    }
}
