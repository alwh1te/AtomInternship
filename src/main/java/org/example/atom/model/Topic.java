package org.example.atom.model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private List<String> messages = new ArrayList<>();
    private int id;
    private String name;

    public List<String> getMessages() {
        return messages;
    }
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    public void addMessage(List<String> message) {
        this.messages.addAll(message);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
