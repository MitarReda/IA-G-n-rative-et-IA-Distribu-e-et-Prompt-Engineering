package com.example.ensetchatbotrag.config;

import jakarta.validation.Valid;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Component
public class RagDataLoader {
    @Value("classpath:/pdfs/cv.pdf")
    private Resource pdfResource;
    @Value("store-data-v1.json")
    private String storeFile;

    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel){
        SimpleVectorStore simpleVectorStore=new SimpleVectorStore(embeddingModel);
        String fileStore= Path.of("src","main","resources","pdfs","cv.pdf")
                .toAbsolutePath()+"/"+storeFile;
        File file =new File(fileStore);

        if(!file.exists()){
            PagePdfDocumentReader pagePdfDocumentReader=new PagePdfDocumentReader(pdfResource);
            List<Document> doc =pagePdfDocumentReader.get();
            TextSplitter textSplitter=new TokenTextSplitter();
            List<Document> chunks= textSplitter.split(doc);
            simpleVectorStore.accept(chunks);
            simpleVectorStore.save(file);
        }else{
            simpleVectorStore.load(file);
        }
        return simpleVectorStore;
    }
}
