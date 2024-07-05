package it.dhoze.WarzoneRAGAI.repository;

import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentRepository {
@Autowired
VectorStore vectorStore;
@Autowired
public DocumentRepository(VectorStore vectorStore) {
    this.vectorStore = vectorStore;
}

public void addDocuments(List<Document> docsToAdd) {
    vectorStore.add(docsToAdd);
}
public List<Document> similaritySearchWithTopK(String prompt, int topK) {
    SearchRequest searchRequest = SearchRequest.query(prompt).withTopK(topK);
    return vectorStore.similaritySearch(searchRequest);
}
}