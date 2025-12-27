package com.dildar.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    // GET  /api/hello?name=Alice
    @GetMapping("/hello")
    public ResponseEntity<ResultDto> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        String message = String.format("Hello, %s!", capitalize(name));
        ResultDto result = new ResultDto("success", message);
        return ResponseEntity.ok(result);
    }

    // GET /api/health
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        return ResponseEntity.ok(status);
    }

    // POST /api/echo  (returns whatever JSON you send under "received")
    @PostMapping("/echo")
    public ResponseEntity<Map<String, Object>> echo(@RequestBody Map<String, Object> payload) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("status", "ok");
        resp.put("received", payload);
        return ResponseEntity.ok(resp);
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    // Simple DTO included as an inner class so you only need this one file to test.
    public static class ResultDto {
        private String status;
        private String message;

        public ResultDto() {
        }

        public ResultDto(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}