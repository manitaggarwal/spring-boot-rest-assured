package com.manitaggarwal.exercise.service;

import com.manitaggarwal.exercise.controller.request.AddStudentRequest;
import com.manitaggarwal.exercise.entiry.Student;

public interface StudentService {
    Student addStudent(AddStudentRequest addStudentRequest);

    Student getStudent(String studentId);
}
