package org.example.atom.controller;

import org.example.atom.model.Message;
import org.example.atom.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/topics/{id}")
    public ResponseEntity<List<Message>> readMessages(@PathVariable(name = "id") int id) {
        final List<Message> messages = messageService.readMessages(id);

        return messages != null
                ? new ResponseEntity<>(messages, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/topics/{id}/{messageId}")
    public ResponseEntity<Message> readMessages(@PathVariable(name = "id") int id, @PathVariable(name = "messageId") int messageId) {
        final Message message = messageService.readMessage(id, messageId);

        return message != null
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/topics/{id}")
    public ResponseEntity<?> addMessage(@PathVariable("id") int topicId, @RequestBody Message message) {
        final boolean added = messageService.addMessage(topicId, message);

        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @PutMapping(value = "/topics/{id}/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable(name = "messageId") int messageId, @RequestBody Message message) {
        final boolean updated = messageService.updateMessage(messageId, message);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/topics/{id}/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable(name = "messageId") int messageId) {
        final boolean deleted = messageService.deleteMessage(messageId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
