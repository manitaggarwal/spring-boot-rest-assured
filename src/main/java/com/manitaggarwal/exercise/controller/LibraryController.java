package com.manitaggarwal.exercise.controller;

import com.manitaggarwal.exercise.controller.request.AddBookRequest;
import com.manitaggarwal.exercise.controller.request.IssueBookRequest;
import com.manitaggarwal.exercise.controller.response.IssueBookResponse;
import com.manitaggarwal.exercise.entiry.Book;
import com.manitaggarwal.exercise.entiry.IssuedBooks;
import com.manitaggarwal.exercise.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class LibraryController {

    private final BookService bookService;

    @PostMapping(value = "/book",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Book addBookToLibrary(@RequestBody AddBookRequest addBookRequest) {
        return bookService.addBookToLibrary(addBookRequest);
    }

    @GetMapping(value = "/book",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getBookList() {
        return bookService.getBookList();
    }

    @GetMapping(value = "/book/{bookId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(@PathVariable String bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping(value = "/book/all/{studentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IssuedBooks> getBooksForStudent(@PathVariable String studentId) throws Exception {
        return bookService.getBooksForStudent(studentId);
    }

    @PostMapping(value = "/book/issue", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public IssueBookResponse issueBook(@RequestBody IssueBookRequest issueBookRequest) {
        return bookService.issueBook(issueBookRequest);
    }

}
