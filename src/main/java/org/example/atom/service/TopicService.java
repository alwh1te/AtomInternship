package org.example.atom.service;

import org.example.atom.model.Topic;

import java.util.List;

public interface TopicService {
    boolean create(Topic topic);

    Topic findTopicByTitle(String title);

    Topic read(int topicId);

    List<Topic> readAll();

    boolean deleteTopic(int topicId);
}
