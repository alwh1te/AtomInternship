package org.example.atom.service;

import org.example.atom.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> readMessages(int topicId);
    Message readMessage(int topicId, int messageId);
    boolean addMessage(int topicId, Message message);

    boolean updateMessage(int messageId, Message message);

    boolean deleteMessage(int messageId);

}
