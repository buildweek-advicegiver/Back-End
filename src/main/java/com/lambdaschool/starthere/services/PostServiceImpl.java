package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Post;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "postService")
public class PostServiceImpl implements PostService
{
    @Autowired
    private PostRepository postRepo;

    //read operations
    @Override
    public List<Post> findAll() {
        List<Post> list = new ArrayList<>();
       postRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Post> findPostsByLoggedInUser(User user) {
        List<Post> list = new ArrayList<>();
        postRepo.findAllByOwner(user).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Post findByPostId(long id) {
        return postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(Long.toString(id)));
    }

    //delete operation
    @Transactional
    @Override
    public void delete(long id) {
        if(postRepo.findById(id).isPresent()){
            postRepo.deleteById(id);
        }else{
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    //create operation
    @Transactional
    @Override
    public Post save(Post post) {
        Post NewPost = new Post();
        NewPost.setDescription(post.getDescription());
        NewPost.setOwner(post.getOwner());
        NewPost.setTitle(post.getTitle());
        NewPost.setPosttype(post.getPosttype());
        return postRepo.save(NewPost);
    }

    //edit operation

    @Transactional
    @Override
    public Post update(Post p, long id) {
        Post temp = postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(Long.toString(id)));

        if(p.getDescription()!=null)
            temp.setDescription(p.getDescription());
        if(p.getTitle()!=null)
            temp.setTitle(p.getTitle());
        if(p.getPosttype()!=null)
            temp.setPosttype(p.getPosttype());

        if(p.getOwner()!=null)
            temp.setOwner(p.getOwner());
        return postRepo.save(temp);
    }

}
