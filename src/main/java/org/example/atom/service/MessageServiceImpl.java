package org.example.atom.service;

import org.example.atom.model.Message;
import org.example.atom.model.User;
import org.example.atom.repository.MessageRepository;
import org.example.atom.repository.TopicRepository;
import org.example.atom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Message> readMessages(int topicId) {
        return messageRepository.findByTopicId(topicId);
    }

    @Override
    public Message readMessage(int messageId) {
        return messageRepository.findMessageByMessageId(messageId);
    }

    @Override
    public User getAuthor(Message message) {
        return userRepository.findByUsername(message.getAuthorName());
    }

    @Override
    public boolean addMessage(int topicId, Message message) {
        if (topicRepository.existsById(topicId)) {
            message.setTopicId(topicId);
            messageRepository.save(message);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMessage(int topicId, int messageId, Message message) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            message.setTopicId(topicId);
            messageRepository.save(message);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMessage(int messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return true;
        }
        return false;
    }
}
