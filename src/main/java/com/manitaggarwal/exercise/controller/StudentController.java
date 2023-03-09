package com.manitaggarwal.exercise.controller;

import com.manitaggarwal.exercise.controller.request.AddStudentRequest;
import com.manitaggarwal.exercise.entiry.Student;
import com.manitaggarwal.exercise.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class StudentController {

    private final StudentService studentService;

    @PostMapping(value = "/student",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Student addStudent(@RequestBody AddStudentRequest addStudentRequest) {
        return studentService.addStudent(addStudentRequest);
    }

    @GetMapping(value = "/student/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudentByStudentId(@PathVariable String studentId) {
        return studentService.getStudent(studentId);
    }

}
