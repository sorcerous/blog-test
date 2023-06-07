package com.blog.service;

import com.blog.model.BlogUser;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public BlogUser saveUser(BlogUser blogUser) {
        return userRepository.save(blogUser);
    }
}
