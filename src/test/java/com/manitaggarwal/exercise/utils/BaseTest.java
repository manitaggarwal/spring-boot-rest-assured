package com.manitaggarwal.exercise.utils;

import com.manitaggarwal.exercise.ExerciseApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(classes = {ExerciseApplication.class}, webEnvironment = DEFINED_PORT)
@ActiveProfiles("integration")
public class BaseTest {
}
