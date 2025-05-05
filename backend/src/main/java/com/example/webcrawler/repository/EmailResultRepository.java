package com.example.webcrawler.repository;

import com.example.webcrawler.model.EmailResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EmailResultRepository extends MongoRepository<EmailResult, String> {
    List<EmailResult> findByUrl(String url);
    List<EmailResult> findByEmail(String email);
}
