package org.example.atom.service;

import org.example.atom.model.Topic;
import org.example.atom.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public boolean create(Topic topic) {
        if (topicRepository.findTopicByTitle(topic.getTitle()) != null) {
            return false;
        }
        topicRepository.save(topic);
        return true;
    }

    @Override
    public Topic findTopicByTitle(String title) {
        return topicRepository.findTopicByTitle(title);
    }

    @Override
    public Topic read(int topicId) {
        return topicRepository.getReferenceById(topicId);
    }

    @Override
    public List<Topic> readAll() {
        return topicRepository.findAll();
    }

    @Override
    public boolean deleteTopic(int topicId) {
        if (topicRepository.existsById(topicId)) {
            topicRepository.deleteById(topicId);
            return true;
        }
        return false;
    }
}
