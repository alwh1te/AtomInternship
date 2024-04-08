package org.example.atom.responseSender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TopicRequestManager {
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
            String BASE_URL = "http://localhost:8080/topic";
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

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
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
