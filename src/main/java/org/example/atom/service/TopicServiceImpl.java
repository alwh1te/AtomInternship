package org.example.atom.service;

import org.example.atom.model.Topic;
import org.example.atom.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void create(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public List<String> readMessages(int id) {
//        return TOPIC_MAP.get(id).getMessages();
        return topicRepository.getReferenceById(id).getMessages();
    }

    @Override
    public Topic read(int id) {
//        return TOPIC_MAP.get(id);
        return topicRepository.getReferenceById(id);
    }

    @Override
    public List<String> readAll() {
        List<String> lst = new ArrayList<>();
        for (Topic topic : topicRepository.findAll()) {
            lst.add(topic.getName());
        }
        return lst;
    }

    @Override
    public boolean addMessage(int id, Topic topic) {
        if (topicRepository.existsById(id)) {
            Topic oldTopic = topicRepository.getReferenceById(id);
            oldTopic.addMessage(topic.getMessages());
            topicRepository.save(oldTopic);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMessage(int topicId, int messageId, Topic updatedTopic) {
        if (topicRepository.existsById(topicId)) {
            Topic oldTopic = topicRepository.getReferenceById(topicId);
            oldTopic.removeMessage(messageId);
            oldTopic.addMessage(updatedTopic.getMessages());
            topicRepository.save(oldTopic);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
