package com.manitaggarwal.exercise.dao;

import com.manitaggarwal.exercise.entiry.Book;
import org.springframework.data.repository.ListCrudRepository;

public interface BookRepository extends ListCrudRepository<Book, String> {
}
