package org.example.atom.responseSender;

import java.util.List;

public class RequestBody {
    private String name;
    private List<String> messages;

    public RequestBody(String name, List<String> messages) {
        this.name = name;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public List<String> getMessages() {
        return messages;
    }
}
