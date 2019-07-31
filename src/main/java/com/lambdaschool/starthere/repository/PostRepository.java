package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Post;
import com.lambdaschool.starthere.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<Post, Long>
{
    List<Post> findAll();
    List <Post>findAllByOwner(User user);
}
