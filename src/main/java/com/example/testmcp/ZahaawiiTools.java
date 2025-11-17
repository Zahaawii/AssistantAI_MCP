package com.example.testmcp;

import com.example.testmcp.entity.Blog;
import com.example.testmcp.repositories.BlogRepository;
import com.example.testmcp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ZahaawiiTools {


    @Autowired
    VectorStore vectorStore;

    private static final Logger log = LoggerFactory.getLogger(ZahaawiiTools.class);

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public ZahaawiiTools(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    /*
    These methods below are for the blog
     ===================================
     */
    @McpTool(name = "GetAllBlogPost", description = "Gets all blog post")
    public List<Blog> getAllBlogPost() {
        try {
            log.info("This method is being accessed trying to find all blog post");
            return blogRepository.findAll();
        } catch (Exception e) {
            log.error("Failed to retrieve all the data from the method: getAllBlogPost");
            throw new RuntimeException(e);
        }
    }

    @McpTool(name = "AuthorPost", description = "Get all blog post by author")
    public List<Blog> getAllBlogPostByAuthor(@McpToolParam String author) {
        try {
            log.info("This method is being accessed trying to find author");
            return blogRepository.findAllByUserInfo_Name(author);
        } catch (Exception e) {
            log.error("Failed to retrieve data from method: GetAllBlogPostByAuthor:  {}", author);
            throw new RuntimeException(e);
        }
    }
    /*
     ===================================
     This Methods below are for the ChromaDB
     */

    @McpTool(name = "FindArticles", description = "Gets the nearest similarities of the question")
    public List<Document> getSimiliaritySearch(@McpToolParam String question) throws Exception {

        try {
            log.info("The method getSimiliaritySearch is being accessed with question: {}", question);

            return this.vectorStore.similaritySearch(SearchRequest.builder()
                    .query(question)
                    .topK(5)
                    .build());

        } catch (Exception e) {
            log.error("Failed to retrieve data from the method getSimiliaritySearch: {}", question);
            throw new RuntimeException("Couldn't find anything: " + e.getMessage());
        }
    }

    @McpTool(name = "addArticles", description = "Gives access to add new articles to the vector database")
    public String addArticlesToDatabase(@McpToolParam String id, String content, String tagName, String tagDescription) {
        log.info("This addArticlesToDatabase is being accessed");
        try {
            log.info("Trying to upload document");
            List<Document> documents = List.of(new Document(id, content, Map.of(tagName, tagDescription)));
            vectorStore.add(documents);
            log.info("Success");
            return "Added document to the database";
        } catch (Exception e) {
            log.error("Failed to upload data from the method addArticleToDatabase");
            return "Error adding article to database";
        }
    }

    @McpTool(name = "DeleteArticle", description = "Gives access to delete a article in the vector database")
    public String deleteArticlesFromDatabase(@McpToolParam String id) {
        log.info("This method deleteArticlesFromDatabase is being acceesed");

        try {
            log.info("Trying to delete an article with id: {}", id);
            vectorStore.delete(List.of(id));
            log.info("Deleted the article");
            return "Succesfully deleted: "  + id;
        } catch (Exception e) {
            log.error("Could not delete the article: {} ", id);
            throw new RuntimeException(e);
        }
    }
}
