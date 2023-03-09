package com.manitaggarwal.exercise.service;

import com.manitaggarwal.exercise.controller.request.AddStudentRequest;
import com.manitaggarwal.exercise.dao.StudentRepository;
import com.manitaggarwal.exercise.entiry.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(AddStudentRequest addStudentRequest) {
        return studentRepository.save(getStudentEntity(addStudentRequest));
    }

    @Override
    public Student getStudent(String studentId) {
        return studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("Student not found"));
    }

    private Student getStudentEntity(AddStudentRequest addStudentRequest) {
        return new Student(
                UUID.randomUUID().toString(),
                addStudentRequest.name(),
                addStudentRequest.msisdn(),
                addStudentRequest.email()
        );
    }
}
