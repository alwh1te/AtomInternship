package org.example.atom.responseSender;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopicRequestManager {

    private final String BASE_URL = "http://localhost:8080/topics";


    public List<String> readTopics() {
        return getStringInfo(BASE_URL);
    }

    public List<String> readMessages(int id) {
        return getStringInfo(BASE_URL + '/' + id);
    }

    public List<String> readMessages(String name) {
        return getStringInfo(BASE_URL + '/' + name);
    }

    public void createTopic(String name, List<String> messages) {
        RestTemplate restTemplate = new RestTemplate();
        RequestBody requestBody = new RequestBody(name, messages);
        HttpEntity<RequestBody> requestEntity = new HttpEntity<>(requestBody);
        restTemplate.exchange(BASE_URL, HttpMethod.POST, requestEntity, String.class);
    }

    private List<String> getStringInfo(String URL) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String[]> response = restTemplate.exchange(URL, HttpMethod.GET, null, String[].class);

        String[] stringArray = response.getBody();

        return new ArrayList<>(Arrays.asList(stringArray));
    }
}
