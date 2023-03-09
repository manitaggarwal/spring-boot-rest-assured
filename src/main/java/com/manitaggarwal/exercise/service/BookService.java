package com.manitaggarwal.exercise.service;

import com.manitaggarwal.exercise.controller.request.AddBookRequest;
import com.manitaggarwal.exercise.controller.request.IssueBookRequest;
import com.manitaggarwal.exercise.controller.response.IssueBookResponse;
import com.manitaggarwal.exercise.entiry.Book;
import com.manitaggarwal.exercise.entiry.IssuedBooks;

import java.util.List;

public interface BookService {
    Book addBookToLibrary(AddBookRequest addBookRequest);

    List<Book> getBookList();

    IssueBookResponse issueBook(IssueBookRequest issueBookRequest);

    Book getBook(String bookId);

    List<IssuedBooks> getBooksForStudent(String studentId);
}
