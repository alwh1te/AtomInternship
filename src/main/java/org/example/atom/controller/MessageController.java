package org.example.atom.controller;

import org.example.atom.model.Message;
import org.example.atom.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/topic/{id}/message")
    public List<Message> readMessages(@PathVariable(name = "id") int id) {
        return messageService.readMessages(id);
    }
    @GetMapping(value = "/topic/{topicId}/message/{messageId}")
    public Message readMessage(@PathVariable(name = "messageId") int messageId) {
        return messageService.readMessage(messageId);
    }

    @PutMapping(value = "/topic/{id}/message")
    public ResponseEntity<?> addMessage(@PathVariable("id") int topicId, @RequestBody Message message) {
        final boolean added = messageService.addMessage(topicId, message);

        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @PutMapping(value = "/topic/{topicId}/message/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable(name = "topicId") int topicId, @PathVariable(name = "messageId") int messageId, @RequestBody Message message) {
        final boolean updated = messageService.updateMessage(topicId, messageId, message);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/topic/{id}/message/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable(name = "messageId") int messageId) {
        final boolean deleted = messageService.deleteMessage(messageId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
