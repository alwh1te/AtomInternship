package org.example.atom.controller;

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
    public ResponseEntity<List<Topic>> readTopicsNames() {
        final List<Topic> topics = topicService.readAll();

        return topics != null && !topics.isEmpty()
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

    @PutMapping(value = "/topics/{id}")
    public ResponseEntity<?> addMessage(@PathVariable("id") int id, @RequestBody Topic topic) {
        final boolean added = topicService.addMessage(id, topic);

        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @PutMapping(value = "/topics/{id}/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable(name = "topicId") int topicId, @PathVariable int messageId, @RequestBody Topic topic) {
        final boolean updated = topicService.updateMessage(topicId, messageId, topic);

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
