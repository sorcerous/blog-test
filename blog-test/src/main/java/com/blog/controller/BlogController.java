package com.blog.controller;

import com.blog.model.Blog;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping(path = "/create/{userId}")
    public Blog createBlog(@RequestBody Blog blog, @PathVariable("userId") String userId) {
        Blog blogFromDb = blogService.createBlog(blog, userId);
        return blog;
    }

    @GetMapping(path = "/read/{blogId}")
    public Blog readBlog(@PathVariable("blogId") String blogId) {
        Blog blog = blogService.getBlog(blogId);
        return blog;
    }

    @PutMapping(path = "update/{blogId}/{userId}")
    public Blog updateBlog(@RequestBody Blog Blog, @PathVariable("blogId") String blogId, @PathVariable("userId") String userId) {
        Blog blog = blogService.updateBlog(Blog, blogId, userId);
        return blog;
    }

    @DeleteMapping(path = "/delete/{blogId}")
    public String deleteBlog(@PathVariable("blogId") String blogId) {
        blogService.deleteBlog(blogId);
        return "Deleted";
    }

    @GetMapping(path = "/all/{parentId}")
    public Map<String, List<Blog>> getAll(@PathVariable("parentId") String parentId) {
        return blogService.getAll(parentId);
    }

}
