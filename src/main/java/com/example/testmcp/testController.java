package com.example.testmcp;


import com.example.testmcp.entity.Blog;
import com.example.testmcp.repositories.BlogRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/*
This controller is right now for test.
It has been made with the purpose to test if the rest client gets a result and being able to print it.
Right now it is possible for the rest client to store the data in the documents list
We need to implement it in the MCP class, but we will wait till tomorrow

(TODO: DO IT IN TEST ENVIRONMENT)
Furthermore, it is for me to test if my methods gets returned as I intended
 */

@RestController
public class testController {

    @Autowired
    VectorStore vectorStore;

    private final BlogRepository blogRepository;

    public testController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("")
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @GetMapping("/test")
    public List<Document> test() {
//        List<Document> documents = List.of(
//                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta1", "meta1")),
//                new Document("The World is Big and Salvation Lurks Around the Corner"),
//                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2")));
//
//        vectorStore.add(documents);

        List<Document> results = vectorStore.similaritySearch("spring");
        return results;
    }
}
