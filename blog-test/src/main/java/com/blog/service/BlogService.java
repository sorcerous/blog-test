package com.blog.service;

import com.blog.model.Blog;
import com.blog.model.BlogUser;
import com.blog.repository.BlogRepository;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    public Blog createBlog(Blog blog, String userId) {
        Optional<BlogUser> blogUserOptional = userRepository.findById(userId);
        if (blogUserOptional.isPresent()) {
            BlogUser blogUser = blogUserOptional.get();
            blog.setCreatedBy(blogUser);
            blog.setModifiedBy(userId);
            blog.setCreatedTs(Timestamp.valueOf(LocalDateTime.now()));
            blog.setModifiedTs(Timestamp.valueOf(LocalDateTime.now()));
            return blogRepository.save(blog);
        }
        return null;
    }

    public Blog getBlog(String blogId) {
        return blogRepository.findById(blogId).orElse(null);
    }

    public Blog updateBlog(Blog blog, String blogId, String userId) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        Optional<BlogUser> optionalUser = userRepository.findById(userId);
        if (blogOptional.isPresent() && optionalUser.isPresent()) {
            Blog blogFromDb = blogOptional.get();
            blogFromDb.setContent(blog.getContent());
            blogFromDb.setModifiedTs(Timestamp.valueOf(LocalDateTime.now()));
            blogFromDb.setModifiedBy(optionalUser.get().getUserId());
            return blogRepository.save(blogFromDb);
        }
        return null;
    }

    public void deleteBlog(String blogId) {
        blogRepository.deleteById(blogId);
    }

    public Map<String, List<Blog>> getAll(String parentId) {
        Map<String, List<Blog>> blogMap = new HashMap<>();
        List<Blog> blogs = blogRepository.getAllChild(parentId);
        blogMap.put("parent", List.of(blogs.remove(0)));
        blogMap.put("children", blogs);
        return blogMap;
    }
}
