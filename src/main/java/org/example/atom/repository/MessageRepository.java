package org.example.atom.repository;

import org.example.atom.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByTopicId(int topicId);
    Message findMessageByMessageId(int messageId);
}
