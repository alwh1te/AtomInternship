package org.example.atom.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.atom.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.atom.service.TopicService;

import java.util.List;

@RestController
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping(value = "/topics")
    public ResponseEntity<?> create(@RequestBody Topic topic) {
        if (topic.getMessages().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        topicService.create(topic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value = "/topics")
    public ResponseEntity<List<String>> readTopicsNames() {
        final List<String> topics = topicService.readAll();

        return topics != null &&  !topics.isEmpty()
                ? new ResponseEntity<>(topics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/topics/{id}")
    public ResponseEntity<List<String>> readMessages(@PathVariable(name = "id") int id) {
        final List<String> messages = topicService.readMessages(id);

        return messages != null
                ? new ResponseEntity<>(messages, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/topics")
    public ResponseEntity<?> addMessage(@RequestBody String request) {
        ObjectMapper mapper = new ObjectMapper();
        int id;
        Topic topic;
        try {
            JsonNode node = mapper.readTree(request);
            id = node.get("id").asInt();
            topic = mapper.convertValue(node, Topic.class);

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        final boolean updated = topicService.addMessage(id, topic);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/topics/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        boolean deleted = topicService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
