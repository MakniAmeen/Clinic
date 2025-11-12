package com.clinic.appointment.Service;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Component
public class GenericHealthAPIConnector {

    private final RestTemplate restTemplate;

    public GenericHealthAPIConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public <T> T callAPI(String endpoint, HttpMethod method, Object requestBody,
                         Class<T> responseType, Map<String, String> headers) {

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            headers.forEach(httpHeaders::set);

            HttpEntity<Object> entity = new HttpEntity<>(requestBody, httpHeaders);

            ResponseEntity<T> response = restTemplate.exchange(
                    endpoint, method, entity, responseType);

            System.out.println("✅ API Call Successful: " + endpoint);
            return response.getBody();

        } catch (Exception e) {
            System.out.println("❌ API Call Failed: " + endpoint + " - " + e.getMessage());
            throw new RuntimeException("API call failed: " + e.getMessage());
        }
    }


    public Map<String, String> authenticate(String authType, String credentials) {
        Map<String, String> headers = new HashMap<>();

        switch (authType.toUpperCase()) {
            case "OAUTH2":
                headers.put("Authorization", "Bearer " + credentials);
                break;
            case "API_KEY":
                headers.put("X-API-Key", credentials);
                break;
            case "BASIC":
                headers.put("Authorization", "Basic " + credentials);
                break;
            default:
                headers.put("Authorization", credentials);
        }

        headers.put("Content-Type", "application/json");
        return headers;
    }


    public boolean testConnection(String endpoint, Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            headers.forEach(httpHeaders::set);

            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            ResponseEntity<String> response = restTemplate.exchange(
                    endpoint, HttpMethod.GET, entity, String.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}