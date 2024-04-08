package org.example.atom.responseSender;

import java.time.LocalDate;

public class RequestBodyMessage {
    private final String author;
    private final String text;
    private final LocalDate date;
    private final int topic_id;

    public RequestBodyMessage(String author, String text, LocalDate date, int topicId) {
        this.author = author;
        this.text = text;
        this.date = date;
        topic_id = topicId;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTopic_id() {
        return topic_id;
    }
}
