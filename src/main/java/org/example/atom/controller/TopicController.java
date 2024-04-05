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
    public ResponseEntity<List<Topic>> read() {
        final List<Topic> topics = topicService.readAll();

        return topics != null &&  !topics.isEmpty()
                ? new ResponseEntity<>(topics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/topics/{id}")
    public ResponseEntity<Topic> read(@PathVariable(name = "id") int id) {
        final Topic topic = topicService.read(id);

        return topic != null
                ? new ResponseEntity<>(topic, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/topics/{id}")
    public ResponseEntity<?> addMessage(@PathVariable("id") int id, @RequestBody Topic topic) {
//        List<String> messages = newTopic.getMessages();
//        messages.add(message);
//        newTopic.setMessages(messages);
        final boolean updated = topicService.addMessage(id, topic);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

//    @PutMapping(value = "/topics/{id}")
//    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Topic newTopic) {
//        final boolean updated = topicService.update(id, newTopic);
//
//        return updated
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }

    @DeleteMapping(value = "/topics/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = topicService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
