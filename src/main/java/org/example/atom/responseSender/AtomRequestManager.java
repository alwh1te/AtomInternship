package org.example.atom.responseSender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.atom.exceptions.*;
import org.example.atom.model.Message;
import org.example.atom.model.Topic;
import org.example.atom.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class AtomRequestManager {

    public User login(String username, String password) throws AuthException {
//        User user = userService.findUserByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }

        throw new UserNotFoundException("No user exist by username = " + username + " or password is incorrect");
    }

    public User register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        sendPostRequest("/register", user);
        return user;
    }

    public void createTopic(String title, Message message) throws UnexpectedItemException {
        if (title.isEmpty() || message == null || message.getText().isEmpty() || message.getAuthor().isEmpty()) {
            throw new TopicCreationError("bad data, title is empty or message is incorrect");
        }
        Topic topic = new Topic();
        topic.setTitle(title);
        sendPostRequest("/topic", topic);
//        topic = topicRepository.findByTitle(title);
        addMessage(topic.getTopicId(), message);
    }

    public void addMessage(int topicId, Message message) throws MessageCreationException {
        if (topicId < 0 || message == null || message.getText().isEmpty() || message.getAuthor().isEmpty()) {
            throw new MessageCreationException("Invalid input data");
        }
        sendPutRequest("/topic/" + topicId + "/message", message);
    }

    public void getUserByUsername(String username) {
        sendGetRequest("/admin/" + username);
    }

//    private boolean isAdmin(User user) {
//        User newUser = userService.findUserByUsername(user.getUsername());
//        return newUser != null && newUser.getRole().equals(Role.ADMIN);
//    }

    public void sendGetRequest(String endpoint) {
        sendRequest(endpoint, "GET", null);
    }

    public void sendPostRequest(String endpoint, Object requestBody) {
        sendRequest(endpoint, "POST", requestBody);
    }

    public void sendPutRequest(String endpoint, Object requestBody) {
        sendRequest(endpoint, "PUT", requestBody);
    }

    public void sendDeleteRequest(String endpoint) {
        sendRequest(endpoint, "DELETE", null);
    }

    private void sendRequest(String endpoint, String method, Object requestBody) {
        try {
            String BASE_URL = "http://localhost:8080";
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonPayload = convertObjectToJson(requestBody);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }

            connection.disconnect();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String convertObjectToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
