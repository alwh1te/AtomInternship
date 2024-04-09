package org.example.atom.service;

import org.example.atom.model.Message;
import org.example.atom.model.Topic;
import org.example.atom.model.User;

import java.util.List;

public interface MessageService {
    List<Message> readMessages(int topicId);

    Message readMessage(int messageId);

    User getAuthor(Message message);

    boolean addMessage(int topicId, Message message);

    boolean updateMessage(int topicId, int messageId, Message message);

    boolean deleteMessage(int messageId);
}
