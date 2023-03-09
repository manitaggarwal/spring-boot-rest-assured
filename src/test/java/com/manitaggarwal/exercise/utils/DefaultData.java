package com.manitaggarwal.exercise.utils;


import com.manitaggarwal.exercise.controller.request.AddBookRequest;
import com.manitaggarwal.exercise.controller.request.AddStudentRequest;


public class DefaultData {

    public static AddStudentRequest getAdminRequest(String msisdn, String email, String name) {
        return new AddStudentRequest(name, msisdn, email);
    }

    public static AddBookRequest getBookRequest(String bookName, String isbn10) {
        return new AddBookRequest(bookName, isbn10);
    }

}

