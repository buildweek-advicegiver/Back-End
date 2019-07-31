package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.AuthorService;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DataController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

//    GET /books - returns a JSON object list of all the books and their authors.
    @ApiOperation(value = "Retrieves a paginated list of all books", response=Book.class, responseContainer = "List")
    @ApiImplicitParams({
           @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query", value = "Results page you want to retrieve (0..N)"),
           @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
           @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                             value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")})
    @GetMapping(value="/books")
    public ResponseEntity<?> getAllBooksPageable(@PageableDefault(page = 0, size = 5) Pageable pageable){
        return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
    }
//    GET /authors - returns a JSON object list of all the authors and their books.
    @ApiOperation(value = "Retrieves list of all authors", response= Author.class, responseContainer = "List")
    @ApiImplicitParams({
           @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query", value = "Results page you want to retrieve (0..N)"),
           @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
           @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                             value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")})
    @GetMapping(value="/authors")
    public ResponseEntity<?> getAllAuthorsPageable(@PageableDefault(page = 0, size = 5) Pageable pageable){
        return new ResponseEntity<>(authorService.findAll(pageable), HttpStatus.OK);
    }
//    PUT /data/books/{id} - updates a books info (Title, Copyright, ISBN) but does NOT have to assign authors to the books
    @ApiOperation(value = "Updates Book with given ID", response=Book.class)
    @PutMapping(value="/data/books/{id}")
    public ResponseEntity<?> updateBookWithId(@Valid @RequestBody Book book, @PathVariable long id){
        return new ResponseEntity<>(bookService.update(book, id), HttpStatus.OK);
    }
//    POST /data/books/authors{id} - assigns a book already in the system to an author already in the system (see how roles are handled for users)
    @ApiOperation(value = "Adds Author with {authorid} to Book with {bookid}", response=Book.class)
    @PostMapping(value="/data/book/{bookid}/author/{authorid}")
    public ResponseEntity<?> addAuthorToBook(@PathVariable long bookid, @PathVariable long authorid){
        return new ResponseEntity<>(bookService.addAuthor(bookid, authorid), HttpStatus.OK);
    }
//    DELETE /data/books/{id} - deletes a book and the book author combinations - but does not delete the author records.
    @ApiOperation(value = "deletes Book with {bookid}")
    @DeleteMapping(value="/data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id){
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
