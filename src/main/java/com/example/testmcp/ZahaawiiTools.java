package com.example.testmcp;

import com.example.testmcp.entity.Blog;
import com.example.testmcp.repositories.BlogRepository;
import com.example.testmcp.repositories.UserRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZahaawiiTools {


        private final BlogRepository blogRepository;
        private final UserRepository userRepository;

    public ZahaawiiTools(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @McpTool(name = "GetAllBlogPost", description = "This descriptions gets you all blog post")
    public List<Blog> getAllBlogPost() {
        return blogRepository.findAll();
    }

    @McpTool(name = "AuthorPost", description = "Get all blog post by author")
    public List<Blog> getAllBlogPostByAuthor(@McpToolParam String author) {
        return blogRepository.findAllByUserInfo_Name(author);
    }

    /*
    test data:
#insert into users (user_Id, created_Date, email, img_Path, name, password, roles) VALUES (1, null, "zahaa", "zahaa", "zahaa", "1234", "ROLE_ADMIN");
#insert into blog (blog_id, body, category, publish_date, subject, user_id) VALUES (1, "hey", "hey", null, "hey", 1);
     */

}
