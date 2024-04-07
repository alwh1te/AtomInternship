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

    @PostMapping(value = "/topic")
    public ResponseEntity<?> create(@RequestBody Topic topic) {
        topicService.create(topic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value = "/topic")
    public ResponseEntity<List<Topic>> readAll() {
        final List<Topic> topics = topicService.readAll();

        return topics != null && !topics.isEmpty()
                ? new ResponseEntity<>(topics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(value = "/topic/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable(name = "id") int id) {
        boolean deleted = topicService.deleteTopic(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
