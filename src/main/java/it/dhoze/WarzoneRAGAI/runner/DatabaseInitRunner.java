package it.dhoze.WarzoneRAGAI.runner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import it.dhoze.WarzoneRAGAI.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RequiredArgsConstructor


@Slf4j
public class DatabaseInitRunner implements ApplicationRunner {
    private final DocumentRepository documentRepository;
   
    @Value("classpath:speciale_lodaut.txt")
    private Resource ndaResource;


@Override
    public void run(ApplicationArguments args) {
        
        String contentAsString = null;
        try {
            contentAsString = ndaResource.getContentAsString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Document> documents = Collections.singletonList(new Document(contentAsString));
        log.info("Adding documents to vector store");
        documents.forEach(doc -> log.debug("Document: {}", doc));
        documentRepository.addDocuments(documents);
        log.info("done!");
    }
}

