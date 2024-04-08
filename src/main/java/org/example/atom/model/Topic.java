package org.example.atom.model;

import jakarta.persistence.*;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @Column(name = "topic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topicId;
    @Column(name = "title")
    private String title;

    public int getTopicId() {
        return topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }
}
