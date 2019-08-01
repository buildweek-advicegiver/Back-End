# Advice-Giver backend root API
https://theadvice-giver.herokuapp.com

## Sign Up endpoint
https://theadvice-giver.herokuapp.com/signup

### required fields

{
     "age": 66,
      "email": "example@example.com",
      "firstname": "john",
      "gender": "Male",
      "lastname": "Doe ",
      "password": "1234qaz",
      "username": "john",
      "usertype": "Advice seeker"
        
}

## Sign Up endpoint
https://theadvice-giver.herokuapp.com/signin
### authentication type: Oauth 2.0
### required fields: username and password

## User Endpoints
https://theadvice-giver.herokuapp.com/users  
GET: /users ==> list all users   
GET: /user/{userid}==> get user by userid   
GET: /getusername ==> returns a user object with account status    
PUT : /user/{userid}==> edit user info       
DELETE: /user/{userid} ==> delete user with a given id     

## POST  Endpoints
https://theadvice-giver.herokuapp.com/post
GET: /feed ==> post home page 
GET: /{postid} ==> get a post by postid
GET: /myposts ==> lists a post by logged in user
POST: /add ==> add a new post ( logged in user as an owner)
required fields 
{
      "title": "example title", 
      "description": "example description", 
      "Posttype": "example post type : accounting "
        
}
PUT: /edit/{postid} ==> edit a post by id 
DELETE: /delete/{postid} ==> delete post by id


# Libraries and Frameworks Used
Spring MVC, Spring Boot, Tomcat , Maven, swagger, hibernate, postgreSQL

