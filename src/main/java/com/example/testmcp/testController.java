package com.example.testmcp;


import com.example.testmcp.BlogFeature.DTO.AuthLogin;
import com.example.testmcp.BlogFeature.entity.Blog;
import com.example.testmcp.repositories.BlogRepository;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;


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

}
