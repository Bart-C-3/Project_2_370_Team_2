package com.example.webcrawler.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "emails")
public class EmailResult {
    @Id
    private String id;
    private String url;
    private String email;

    public EmailResult() {}
    public EmailResult(String url, String email) {
        this.url = url;
        this.email = email;
    }
    public String getId() { return id; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
