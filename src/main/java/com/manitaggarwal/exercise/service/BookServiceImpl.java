package com.manitaggarwal.exercise.service;

import com.manitaggarwal.exercise.controller.request.AddBookRequest;
import com.manitaggarwal.exercise.controller.request.IssueBookRequest;
import com.manitaggarwal.exercise.controller.response.IssueBookResponse;
import com.manitaggarwal.exercise.dao.BookRepository;
import com.manitaggarwal.exercise.dao.IssuedBooksRepository;
import com.manitaggarwal.exercise.dao.StudentRepository;
import com.manitaggarwal.exercise.entiry.Book;
import com.manitaggarwal.exercise.entiry.IssuedBooks;
import com.manitaggarwal.exercise.entiry.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final StudentRepository studentRepository;

    private final IssuedBooksRepository issuedBooksRepository;

    @Override
    public Book addBookToLibrary(AddBookRequest addBookRequest) {

        return bookRepository.save(new Book(addBookRequest.isbn10(), addBookRequest.bookName()));
    }

    @Override
    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    @Override
    public IssueBookResponse issueBook(IssueBookRequest issueBookRequest) {

        // fist get student to which a book should be issued
        Student student = studentRepository.findById(issueBookRequest.StudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // getting the book to be issued
        Book book = bookRepository.findById(issueBookRequest.bookCode())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // getting the issueDate
        Date issueDate = new Date();

        // checking if book is already issued
        Optional<IssuedBooks> bookIssued = issuedBooksRepository.findByBook(book);

        if (bookIssued.isPresent()) {
            // then throw an error and don't proceed to issue the book again
            throw new RuntimeException("This book has already been issued.");
        }


        // saving to db that an issue was made
        issuedBooksRepository.save(issueBookEntity(student, book, issueDate));

        // returning the response
        return new IssueBookResponse(student.getStudentName(), book.getBookName(), issueDate);

    }

    @Override
    public Book getBook(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("book not found"));
    }

    /*
     * Get all the books issued to s student
     * */
    @Override
    public List<IssuedBooks> getBooksForStudent(String studentId) {

        /*
         * We need a student to find all the books he has got issued, but we have only his ID
         * */

        // from using student id we are getting, the studentOptional
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        // if the student exists, the find the books that he has got
        if (studentOptional.isPresent()) {
            return issuedBooksRepository.findAllByStudent(studentOptional.get());
        } else {
            // if student does not exist throw an error
            throw new RuntimeException("Student does not exist");
        }

    }

    public IssuedBooks issueBookEntity(Student student, Book book, Date issueDate) {
        return new IssuedBooks(
                UUID.randomUUID().toString(),
                student, book, issueDate);
    }
}
