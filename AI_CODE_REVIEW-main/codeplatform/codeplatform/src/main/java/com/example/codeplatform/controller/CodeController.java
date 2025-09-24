package com.example.codeplatform.controller;

import com.example.codeplatform.model.CodeRequest;
import com.example.codeplatform.model.CodeResponse;
import com.example.codeplatform.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Base path for all endpoints
public class CodeController {

    @Autowired
    private CodeService codeService;

    // Endpoint to run the code
    @PostMapping(value = "/run", produces = MediaType.APPLICATION_JSON_VALUE)
    public CodeResponse runCode(@RequestBody CodeRequest request) {
        String output = codeService.runCode(
                request.getCode(),
                request.getLanguage(),
                request.getInput()
        );
        return new CodeResponse(output);
    }

    // Endpoint to save the code
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public CodeResponse saveCode(@RequestBody CodeRequest request) {
        String output = codeService.saveCode(
                request.getCode(),
                request.getLanguage()
        );
        return new CodeResponse(output);
    }

    // Endpoint to review the code
    @PostMapping(value = "/review", produces = MediaType.APPLICATION_JSON_VALUE)
    public CodeResponse reviewCode(@RequestBody CodeRequest request) {
        String reviewOutput = codeService.reviewCode(
                request.getCode(),
                request.getLanguage()
        );
        return new CodeResponse(reviewOutput);
    }
}
