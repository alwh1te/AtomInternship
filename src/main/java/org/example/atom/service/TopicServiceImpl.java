package org.example.atom.service;

import org.example.atom.model.Message;
import org.example.atom.model.Topic;
import org.example.atom.repository.MessageRepository;
import org.example.atom.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void create(Topic topic) {
        topicRepository.save(topic);
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
        topicRepository.deleteById(topicId);
        return true;
    }

//    @Override
//    public List<Message> readMessages(int topicId) {
//        return topicRepository.getReferenceById(topicId).getMessages();
//    }
//
//    @Override
//    public Message readMessage(int topicId, int messageId) {
//        return topicRepository.getReferenceById(topicId).getMessage(messageId - 1);
//    }
//
//    @Override
//    public Topic read(int topicId) {
//        return topicRepository.getReferenceById(topicId);
//    }
//
//    @Override
//    public List<Topic> readAll() {
//        return topicRepository.findAll();
//    }
//
//    @Override
//    public boolean addMessage(int topicId, Message message) {
//        if (topicRepository.existsById(topicId)) {
//            Topic topic = topicRepository.getReferenceById(topicId);
//            topic.addMessage(message);
//            topicRepository.save(topic);
//            return true;
//        }
//        return false;
//    }
//    @Override
//    public boolean updateMessage(int topicId, int messageId, Message message) {
//        if (topicRepository.existsById(topicId)) {
//            Topic topic = topicRepository.getReferenceById(topicId);
//            topic.updateMessage(messageId - 1, message);
//            topicRepository.save(topic);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteMessage(int topicId, int messageId) {
//        if (topicRepository.existsById(topicId) && !topicRepository.getReferenceById(topicId).getMessages().isEmpty()) {
//            Topic oldTopic = topicRepository.getReferenceById(topicId);
//            oldTopic.removeMessage(messageId - 1);
//            topicRepository.save(oldTopic);
//            return true;
//        }
//        return false;
//    }
//
//
//    @Override
//    public boolean deleteTopic(int id) {
//        if (topicRepository.existsById(id)) {
//            topicRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
}
