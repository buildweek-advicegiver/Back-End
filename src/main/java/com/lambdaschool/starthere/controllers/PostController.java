package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Post;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.services.PostService;
import com.lambdaschool.starthere.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController
{
    @Autowired
   private PostService postService;
    @Autowired
    private UserService userService;

    //list all posts
    @GetMapping(value = "/feed",
            produces = {"application/json"})
    public ResponseEntity<?> getFeed(){
        List<Post> list=postService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //find a post by postid
    @GetMapping(value = "/{postid}",
            produces = {"application/json"})
    public ResponseEntity<?> getPostById(@PathVariable long postid){
        Post p = postService.findByPostId(postid);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }
    //find posts by logged in user
    @GetMapping(value = "/myposts",
            produces = {"application/json"})
    public ResponseEntity<?> getMyPosts(){
        User user=userService.myposts();
        List<Post> res = postService.findPostsByLoggedInUser(user);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

   //add a post

    @PostMapping(value = "/add",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addPostToUser(@RequestBody Post p){
        User user= userService.myposts();
        p.setOwner(user);
        p=postService.save(p);

        HttpHeaders headers=new HttpHeaders();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postid}").buildAndExpand(p.getPostid()).toUri();
        headers.setLocation(uri);

        return  new ResponseEntity<>(null,headers,HttpStatus.CREATED);
    }

    //Edit post

    @PutMapping(value="/{postid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> updatePost(@RequestBody Post p, @PathVariable long postid){
        Post ret=postService.update(p,postid);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

    //Delete Post
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(HttpServletRequest request,@PathVariable long id)
    {
               userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
