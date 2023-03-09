package com.manitaggarwal.exercise.dao;

import com.manitaggarwal.exercise.entiry.Book;
import com.manitaggarwal.exercise.entiry.IssuedBooks;
import com.manitaggarwal.exercise.entiry.Student;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface IssuedBooksRepository extends ListCrudRepository<IssuedBooks, String> {


    List<IssuedBooks> findAllByStudent(Student student);

    Optional<IssuedBooks> findByBook(Book book);


}
