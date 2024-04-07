package org.example.atom.service;

import org.example.atom.model.Topic;

import java.util.List;

public interface TopicService {
    void create(Topic topic);
    Topic read(int topicId);
    List<Topic> readAll();
    boolean deleteTopic(int topicId);
}
