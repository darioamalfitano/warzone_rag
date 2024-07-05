package it.dhoze.WarzoneRAGAI.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
@Slf4j
@Service
public class ChatGeneratorService {
private final OllamaChatModel chatModel;
@Autowired
public ChatGeneratorService(OllamaChatModel chatModel ) {
    this.chatModel = chatModel;
}

public ChatResponse call(Prompt prompt) {
    log.info(prompt.toString());
    return this.chatModel.call(prompt);
}
public Flux<ChatResponse> generateStream(Prompt prompt) {
    return this.chatModel.stream(prompt);
}
}