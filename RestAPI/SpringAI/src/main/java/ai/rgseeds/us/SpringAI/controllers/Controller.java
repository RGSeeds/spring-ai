package ai.rgseeds.us.SpringAI.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final String DEFAULT_REQUEST = "What is the simple definition of an AI Advisor in 1 sentence";

    private final String DEFAULT_RESPONSE_HEADER = "------------------\n Advisor Response \n------------------\n%s";

    private final ChatClient chatClient;

    //Spring AI does not seem to like ChatClient injection here
    public Controller(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/advisor")
    public String defaultRequest(@RequestParam(value="request", defaultValue = DEFAULT_REQUEST) String request) {

        return DEFAULT_RESPONSE_HEADER.formatted( chatClient.prompt().user(request).call().content() );

    }

}
