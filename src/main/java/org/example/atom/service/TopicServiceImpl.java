package org.example.atom.service;

import org.example.atom.model.Topic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TopicServiceImpl implements TopicService {

    private static final Map<Integer, Topic> TOPIC_MAP = new HashMap<>();
    private static final Map<String, Integer> TOPIC_NAME_ID_MAP = new HashMap<>();
    private static final AtomicInteger TOPIC_ID_HOLED = new AtomicInteger();

    @Override
    public void create(Topic topic) {
        final int topicId = TOPIC_ID_HOLED.incrementAndGet();
        topic.setId(topicId);
        TOPIC_MAP.put(topicId, topic);
    }

    @Override
    public List<String> readMessages(int id) {
        return TOPIC_MAP.get(id).getMessages();
    }


    @Override
    public Topic read(int id) {
        return TOPIC_MAP.get(id);
    }

    @Override
    public List<String> readTopicsNames() {
        List<String> lst = new ArrayList<>();
        for (Topic topic : TOPIC_MAP.values()) {
            lst.add(topic.getName());
        }
        return lst;
    }

    @Override
    public boolean addMessage(int id, Topic topic) {
        if (TOPIC_MAP.containsKey(id)) {
            TOPIC_MAP.get(id).addMessage(topic.getMessages());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(int id, Topic updatedTopic) {
        if (TOPIC_MAP.containsKey(id)) {
            TOPIC_MAP.get(id).setMessages(updatedTopic.getMessages());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return TOPIC_MAP.remove(id) != null;
    }
}
