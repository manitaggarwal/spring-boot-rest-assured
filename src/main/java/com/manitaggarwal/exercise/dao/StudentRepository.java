package com.manitaggarwal.exercise.dao;

import com.manitaggarwal.exercise.entiry.Student;
import org.springframework.data.repository.ListCrudRepository;

public interface StudentRepository extends ListCrudRepository<Student, String> {
}
