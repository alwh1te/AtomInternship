package org.example.atom.responseSender;

import org.example.atom.model.Message;

import java.util.List;

public class RequestBody {
    private String name;
    private List<Message> messages;

    public RequestBody(String name, List<Message> messages) {
        this.name = name;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
