package com.example.webcrawler.service;

import com.example.webcrawler.model.EmailResult;
import com.example.webcrawler.repository.EmailResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Service

public class EmailCrawlerService {
    @Autowired
    private EmailResultRepository emailResultRepository;

    // Nutch integration is commented out for now
    // @Autowired
    // private NutchIntegrationService nutchIntegrationService;

    // @Value("${nutch.crawl.dir:/tmp/nutch-crawl}")
    // private String crawlDir;

    // Simulate crawling and extracting emails from a given HTML/text

    public Set<EmailResult> extractEmails(String url, String content, Set<String> globalEmailSet) {
        Set<EmailResult> results = new HashSet<>();
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcher = emailPattern.matcher(content);
        while (matcher.find()) {
            String email = matcher.group();
            if (!globalEmailSet.contains(email)) {
                EmailResult result = new EmailResult(url, email);
                results.add(result);
                emailResultRepository.save(result);
                globalEmailSet.add(email);
            }
        }
        return results;
    }

    // Fetch the real page and extract emails
    public void startCrawl(String seedUrl) {
        Set<String> visitedUrls = new HashSet<>();
        Set<String> foundEmails = new HashSet<>();
        crawlRecursive(seedUrl, visitedUrls, foundEmails, 0, 2); // maxDepth=2 for demo
    }

    private void crawlRecursive(String url, Set<String> visitedUrls, Set<String> foundEmails, int depth, int maxDepth) {
        if (depth > maxDepth || visitedUrls.contains(url)) {
            return;
        }
        visitedUrls.add(url);
        // Remove any surrounding quotes from the URL
        String cleanedUrl = url;
        if (cleanedUrl != null) {
            cleanedUrl = cleanedUrl.trim();
            if ((cleanedUrl.startsWith("\"") && cleanedUrl.endsWith("\"")) ||
                (cleanedUrl.startsWith("'") && cleanedUrl.endsWith("'"))) {
                cleanedUrl = cleanedUrl.substring(1, cleanedUrl.length() - 1);
            }
        }
        try {
            Document doc = Jsoup.connect(cleanedUrl).get();
            String html = doc.html();
            extractEmails(cleanedUrl, html, foundEmails);
            // Find and crawl links
            doc.select("a[href]").forEach(link -> {
                String absHref = link.absUrl("href");
                if (absHref.startsWith("http")) {
                    crawlRecursive(absHref, visitedUrls, foundEmails, depth + 1, maxDepth);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
