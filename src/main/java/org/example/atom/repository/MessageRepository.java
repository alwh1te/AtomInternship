package org.example.atom.repository;

import org.example.atom.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByTopicId(int topicId);
    Message findByTopicIdAndMessageId(int topicId, int messageId);
}
