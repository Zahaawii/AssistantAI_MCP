package com.example.testmcp.blog;


import com.example.testmcp.BlogFeature.DTO.BlogDTO;
import com.example.testmcp.BlogFeature.Service.BlogServiceAPI;
import com.example.testmcp.mcpService.BlogServiceMCP;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BlogServiceMCPUnitTest {

    @Autowired(required = false)
    private ApplicationContext applicationContext;

    //MCP tools
    @Autowired(required = false)
    private BlogServiceMCP blogServiceMCP;

    //Mocking the data
    @MockitoBean
    private BlogServiceAPI blogServiceAPI;

    // Testing if the application have the correct beans and can boot up
    @Test
    void contextLoads() {
        assertNotNull(applicationContext);
    }

    // Testing to see if the blogService bean exist
    @Test
    void blogServiceTools_shouldBeCreated() {
        assertNotNull(blogServiceAPI, "BlogService should be created");
    }

    // Testing to see if the bean exist
    @Test
    void blogServiceMCP_shouldBeCreated() {
        assertNotNull(blogServiceMCP, "BlogService should be created");
    }

    // Wanted to learn how to test MCP methods, so didnt split it up
    //TODO:: Seperate the test so its not one method.
    @Test
    void mcpToolsAreScannable() {

        //  ---Trying to follow Act Assert Arrange ----

        //  ----ARRANGE----

        //Creating some parameters to test later
        String success = "Blog post deleted";
        String jwtToken = "jwtToken";
        String username = "MCPtest";
        String password = "1234";
        String author = "Zahaawii";
        int id = 100;
        List<BlogDTO> blogsList = List.of(new BlogDTO(0, null, null, null, null, null));

        //Mock the data
        when(blogServiceAPI.login(username, password)).thenReturn(jwtToken);
        when(blogServiceAPI.getAllBlogPost()).thenReturn(blogsList);
        when(blogServiceAPI.getAllByAuthor(author)).thenReturn(blogsList);
        when(blogServiceAPI.deletePostById(100, jwtToken)).thenReturn(success);

        // -----ACT-----
        List<BlogDTO> getAllBlogsresult = blogServiceMCP.getAllBlogPost();
        List<BlogDTO> getAllByAuthorResult = blogServiceMCP.getAllBlogPostByAuthor(author);
        String loginResult = blogServiceMCP.login(username, password);
        String deleteResult = blogServiceMCP.deletePostById(id, jwtToken);


        // ----ASSERT-----

        // Verify that our MCP tools are discoverable by the Spring AI MCP framework
        assertTrue(applicationContext.containsBean("blogServiceMCP"));

        //get all blog
        assertNotNull(getAllBlogsresult);
        assertFalse(getAllBlogsresult.isEmpty());
        verify(blogServiceAPI, atLeastOnce()).getAllBlogPost();

        //get all blog by author
        assertNotNull(getAllByAuthorResult);
        assertFalse(getAllByAuthorResult.isEmpty());
        verify(blogServiceAPI, atLeastOnce()).getAllByAuthor(author);

        // login
        assertNotNull(loginResult);
        assertEquals("jwtToken", loginResult);
        verify(blogServiceAPI, atLeastOnce()).login(username, password);

        //delete blog post
        assertEquals(success, deleteResult);
        verify(blogServiceAPI, atLeastOnce()).deletePostById(id, jwtToken);

//        As I know the below methods will fail, as I do not want to delete a blog post and I need a JWT token, I will test if it fails
//        assertThrows(RuntimeException.class, () -> tools.deletePostById(0, "1234"));
//        assertThrows(RuntimeException.class, () -> tools.createBlogPost(null, null, null, null, null, null));
    }

}
