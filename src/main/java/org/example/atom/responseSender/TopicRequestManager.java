package org.example.atom.responseSender;

import org.example.atom.model.Message;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TopicRequestManager {

    private final String BASE_URL = "http://localhost:8080/topics";


    public List<Message> readTopics() {
        return getStringInfo(BASE_URL);
    }

    public List<Message> readMessages(int id) {
        return getStringInfo(BASE_URL + '/' + id);
    }

    public List<Message> readMessages(String name) {
        return getStringInfo(BASE_URL + '/' + name);
    }

    public void updateMessage(int topicId, int messageId, String message) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<RequestBody> requestEntity = new HttpEntity<>();
//        restTemplate.exchange(BASE_URL, HttpMethod.POST, requestEntity, String.class);
    }

    public void createTopic(String title, List<Message> messages) {
        RestTemplate restTemplate = new RestTemplate();
        RequestBody requestBody = new RequestBody(title, messages);
        HttpEntity<RequestBody> requestEntity = new HttpEntity<>(requestBody);
        restTemplate.exchange(BASE_URL, HttpMethod.POST, requestEntity, String.class);
    }

    private List<Message> getStringInfo(String URL) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Message[]> response = restTemplate.exchange(URL, HttpMethod.GET, null, Message[].class);

        Message[] stringArray = response.getBody();

        return new ArrayList<>(Arrays.asList(stringArray));
    }
}
