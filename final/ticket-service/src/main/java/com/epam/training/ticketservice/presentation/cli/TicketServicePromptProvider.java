package com.epam.training.ticketservice.presentation.cli;

import org.jline.utils.AttributedString;
import org.springframework.stereotype.Component;
import org.springframework.shell.jline.PromptProvider;

@Component
public class TicketServicePromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("Ticket service>");
    }
}
