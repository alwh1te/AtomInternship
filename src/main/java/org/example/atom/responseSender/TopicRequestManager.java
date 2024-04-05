package org.example.atom.responseSender;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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

    public void createTopic(String name, List<String> messages) {

        // Создание объекта RestTemplate для выполнения HTTP-запросов
        RestTemplate restTemplate = new RestTemplate();

        // Создание объекта, представляющего тело запроса
        RequestBody requestBody = new RequestBody(name, messages);

        // Создание HTTP-запроса с телом, содержащим id и messages
        HttpEntity<RequestBody> requestEntity = new HttpEntity<>(requestBody);

        // Отправка POST-запроса на сервер
        restTemplate.exchange(BASE_URL, HttpMethod.POST, requestEntity, String.class);
    }

    private List<String> getStringInfo(String URL) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String[]> response = restTemplate.exchange(URL, HttpMethod.GET, null, String[].class);

        String[] stringArray = response.getBody();

        return new ArrayList<>(Arrays.asList(stringArray));
    }
}
