package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Post;
import com.lambdaschool.starthere.models.User;

import java.util.List;

public interface PostService
{
    //read operations
    List<Post> findAll();
    List<Post>findPostsByLoggedInUser(User user);
    Post findByPostId(long id);

    //Create
    Post save (Post post);

    //Update
    Post update (Post post, long id);

    //Delete
    void delete (long id);
}
