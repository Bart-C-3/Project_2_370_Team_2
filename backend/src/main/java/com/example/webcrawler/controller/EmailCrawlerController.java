package com.example.webcrawler.controller;

import com.example.webcrawler.model.EmailResult;
import com.example.webcrawler.repository.EmailResultRepository;
import com.example.webcrawler.service.EmailCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/crawler")
public class EmailCrawlerController {
    @Autowired
    private EmailResultRepository emailResultRepository;

    @Autowired
    private EmailCrawlerService emailCrawlerService;

    @GetMapping("/emails")
    public List<EmailResult> getAllEmails() {
        return emailResultRepository.findAll();
    }

    // Start a crawl and collect emails
    @PostMapping("/start")
    public ResponseEntity<?> startCrawl(@RequestBody(required = false) String seedUrl) {
        if (seedUrl == null || seedUrl.isEmpty()) {
            return ResponseEntity.badRequest().body("Seed URL is required");
        }
        emailCrawlerService.startCrawl(seedUrl);
        return ResponseEntity.ok("Crawl started for: " + seedUrl);
    }
}
