package com.epam.training.ticketservice.configuration;

import com.epam.training.ticketservice.data.Constants;
import org.jline.utils.AttributedString;
import org.springframework.stereotype.Component;
import org.springframework.shell.jline.PromptProvider;

@Component
public class TicketServicePromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(Constants.PROMPT);
    }
}
