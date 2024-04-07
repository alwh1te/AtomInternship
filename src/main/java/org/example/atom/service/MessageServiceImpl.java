package org.example.atom.service;

import org.example.atom.model.Message;
import org.example.atom.repository.MessageRepository;
import org.example.atom.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Override
    public List<Message> readMessages(int topicId) {
        return messageRepository.findByTopicId(topicId);
    }

    @Override
    public Message readMessage(int topicId, int messageId) {
        return messageRepository.findByTopicIdAndMessageId(topicId, messageId);
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
    public boolean updateMessage(int messageId, Message message) {
        if (messageRepository.existsById(messageId)) {
            Message oldMessage = messageRepository.getReferenceById(messageId);
            oldMessage.setText(message.getText());
            oldMessage.setDate(message.getDate());
            oldMessage.setAuthor(message.getAuthor());
            messageRepository.save(oldMessage);
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
