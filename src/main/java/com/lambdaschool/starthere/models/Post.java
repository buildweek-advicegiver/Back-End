package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postid;
    private String title;
    private String description;
    private String posttype;

    @ManyToOne(fetch = FetchType.LAZY)

    @JsonIgnoreProperties(value ={"posts", "hibernateLazyInitializer", "handler"} )
    private User owner;

    public Post (){}

    public Post(String description, String posttype, String title)
    {
        this.title = title;
        this.description = description;
        this.posttype = posttype;
//        this.owner = owner;
    }

    public long getPostid()
    {
        return postid;
    }

    public void setPostid(long postid)
    {
        this.postid = postid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPosttype()
    {
        return posttype;
    }

    public void setPosttype(String posttype)
    {
        this.posttype = posttype;
    }

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }
}
