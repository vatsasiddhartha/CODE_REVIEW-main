package com.example.codeplatform.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CodeService {

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CodeService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public String runCode(String code, String language, String input) {
        String url = "https://emkc.org/api/v2/piston/execute";

        Map<String, Object> body = new HashMap<>();
        body.put("language", mapLanguage(language));
        body.put("version", "*");

        Map<String, String> file = Map.of(
                "name", "Main." + getFileExtension(language),
                "content", code
        );
        body.put("files", List.of(file));
        body.put("stdin", input);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            Map response = restTemplate.postForObject(url, request, Map.class);
            assert response != null;
            Map<String, Object> run = (Map<String, Object>) response.get("run");
            return (String) run.get("output");
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String saveCode(String code, String language) {
        // This method might need to interact with a database in a real application
        return "Code saved successfully in " + language + "!";
    }

    public String reviewCode(String code, String language) {
        // Ensure you have gemini.api.key=YOUR_ACTUAL_API_KEY in your application.properties
        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + geminiApiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Enhanced prompt for better code review
        String prompt = String.format(
                "Please provide a comprehensive code review for this %s code. " +
                        "Structure your response with clear sections:\n\n" +
                        "**Code Analysis:**\n" +
                        "**Issues Found:**\n" +
                        "**Suggestions for Improvement:**\n" +
                        "**Best Practices:**\n" +
                        "**Overall Rating:**\n\n" +
                        "Code to review:\n```%s\n%s\n```",
                language, language.toLowerCase(), code
        );

        Map<String, Object> part = Map.of("text", prompt);
        Map<String, Object> content = Map.of("parts", List.of(part));
        Map<String, Object> payload = Map.of("contents", List.of(content));

        try {
            String payloadJson = objectMapper.writeValueAsString(payload);
            HttpEntity<String> request = new HttpEntity<>(payloadJson, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, request, Map.class);
            Map<String, Object> body = response.getBody();

            if (body != null && body.containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) body.get("candidates");
                if (!candidates.isEmpty()) {
                    Map<String, Object> candidate = candidates.get(0);
                    Map<String, Object> responseContent = (Map<String, Object>) candidate.get("content");
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) responseContent.get("parts");
                    if (!parts.isEmpty() && parts.get(0).containsKey("text")) {
                        // Return just the text content, not wrapped in JSON
                        return (String) parts.get(0).get("text");
                    } else {
                        return "❌ Failed to extract review text from Gemini response (parts or text missing).";
                    }
                } else {
                    return "❌ No candidates found in Gemini response.";
                }
            } else {
                return "❌ Invalid or empty response from Gemini API. Please check your API key and try again.";
            }

        } catch (Exception e) {
            return "❌ Error while reviewing code: " + e.getMessage() +
                    "\n\nPlease check:\n" +
                    "1. Your Gemini API key is valid\n" +
                    "2. You have sufficient API quota\n" +
                    "3. Your internet connection is stable";
        }
    }

    private String mapLanguage(String language) {
        return switch (language.toLowerCase()) {
            case "java", "python", "c", "cpp", "javascript" -> language.toLowerCase();
            default -> "plain_text";
        };
    }

    private String getFileExtension(String language) {
        return switch (language.toLowerCase()) {
            case "java" -> "java";
            case "python" -> "py";
            case "c" -> "c";
            case "cpp" -> "cpp";
            case "javascript" -> "js";
            default -> "txt";
        };
    }
}