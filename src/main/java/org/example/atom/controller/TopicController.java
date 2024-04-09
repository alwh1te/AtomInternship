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

    @Autowired
    private TopicService topicService;

    @PostMapping(value = "/topic")
    public ResponseEntity<?> create(@RequestBody Topic topic) {
        boolean created = topicService.create(topic);
        return created
                ? new ResponseEntity<>("Topic was successfully created", HttpStatus.CREATED)
                : new ResponseEntity<>("Topic with this title already exist", HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/topic")
    public List<Topic> readAll() {

        return topicService.readAll();
    }

    @GetMapping("/topic/{title}")
    public Topic getTopicByTitle(@PathVariable("title") String title) {
        return topicService.findTopicByTitle(title);
    }

    @DeleteMapping(value = "/topic/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable(name = "id") int id) {
        boolean deleted = topicService.deleteTopic(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
