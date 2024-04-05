package org.example.atom.service;

import org.example.atom.model.Topic;

import java.util.List;

public interface TopicService {
    void create(Topic topic);
    List<String> readMessages(int id);
    Topic read(int id);
    List<Topic> readAll();
    boolean addMessage(int id, Topic topic);

    boolean updateMessage(int topicId, int messageId, Topic updatedTopic);

    boolean delete(int id);
}
