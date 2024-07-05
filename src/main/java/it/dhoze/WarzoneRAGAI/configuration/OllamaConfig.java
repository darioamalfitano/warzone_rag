package it.dhoze.WarzoneRAGAI.configuration;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;

import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class OllamaConfig {

    @Bean
    public OllamaChatModel ollamaChatModel() {
      var ollamaApi = new OllamaApi();
     return new OllamaChatModel(ollamaApi,
          OllamaOptions.create()
              .withModel(OllamaOptions.DEFAULT_MODEL)
              .withTemperature(0.9f));
    }
@Bean
public EmbeddingModel embeddingModel(){
  var ollamaApi = new OllamaApi();
  return new OllamaEmbeddingModel(ollamaApi);
}

}