package org.example.atom.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    @Column(name = "author")
    private String author;

    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private Date date;

//    @ManyToOne
    @Column(name = "topic_id")
    private int topicId;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int id) {
        this.messageId = id;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
