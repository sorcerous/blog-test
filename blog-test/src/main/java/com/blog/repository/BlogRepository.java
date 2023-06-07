package com.blog.repository;


import com.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

    @Query(value = "WITH blog_child AS ( "
            + " SELECT * FROM blog WHERE blog_id = :parentId "
            + " ) "
            + " SELECT * FROM blog_child "
            + " UNION ALL "
            + " SELECT b.* "
            + " FROM blog_child bg "
            + " JOIN blog b ON b.parent_id = bg.blog_id;", nativeQuery = true)
    List<Blog> getAllChild(@Param("parentId") String parentId);
}
