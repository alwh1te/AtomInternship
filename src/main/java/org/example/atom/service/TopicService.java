package org.example.atom.service;

import org.example.atom.model.Topic;

import java.util.List;

public interface TopicService {
    void create(Topic topic);
    List<String> readMessages(int id);
    Topic read(int id);
    List<String> readTopicsNames();
    boolean addMessage(int id, Topic topic);
    boolean update(int id, Topic updatedTopic);
    boolean delete(int id);
}
