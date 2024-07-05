package it.dhoze.WarzoneRAGAI.controller;


import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.dhoze.WarzoneRAGAI.service.ChatGeneratorService;
import it.dhoze.WarzoneRAGAI.service.RagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/assistant")
@RequiredArgsConstructor
public class AssistantController {
    private final ChatGeneratorService chatGeneratorService;
    private final RagService ragService;

    @PostMapping(value = "/prompt")
    public String prompt(@RequestBody String clientPrompt) {
        Prompt prompt = ragService.generatePromptFromClientPrompt(clientPrompt);
        return prompt.toString();
    }
@PostMapping(value = "/chat")
public String chat(@RequestBody String clientPrompt) {
    Prompt prompt = ragService.generatePromptFromClientPrompt(clientPrompt);
    return extractContentFromChatResponse(chatGeneratorService.call(prompt));
}
    private String extractContentFromChatResponse(ChatResponse chatResponse) {
        log.info(chatResponse.getResult().getOutput().getContent());
        return chatResponse.getResult().getOutput().getContent();
    }

}