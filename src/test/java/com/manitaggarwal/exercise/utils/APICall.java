package com.manitaggarwal.exercise.utils;

import com.manitaggarwal.exercise.controller.request.AddBookRequest;
import com.manitaggarwal.exercise.controller.request.AddStudentRequest;
import com.manitaggarwal.exercise.controller.request.IssueBookRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APICall extends BaseTest {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    public static Response addStudent(AddStudentRequest request) {
        RestAssured.baseURI = "http://localhost:40080/api/v1/student";
        RequestSpecification requestSpecification =
                RestAssured.given()
                        .header(CONTENT_TYPE, APPLICATION_JSON)
                        .body(JsonUtils.toJsonString(request));
        return requestSpecification.post();
    }

    public static Response getStudent(String studentId) {
        RestAssured.baseURI = "http://localhost:40080/api/v1/student/" + studentId;
        RequestSpecification requestSpecification =
                RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON);
        return requestSpecification.get();
    }

    public static Response addBook(AddBookRequest request) {
        RestAssured.baseURI = "http://localhost:40080/api/v1/book";
        RequestSpecification requestSpecification =
                RestAssured.given()
                        .header(CONTENT_TYPE, APPLICATION_JSON)
                        .body(JsonUtils.toJsonString(request));
        return requestSpecification.post();
    }

    public static Response issueBookToStudent(IssueBookRequest request) {
        RestAssured.baseURI = "http://localhost:40080/api/v1/book/issue";
        RequestSpecification requestSpecification =
                RestAssured.given()
                        .header(CONTENT_TYPE, APPLICATION_JSON)
                        .body(JsonUtils.toJsonString(request));
        return requestSpecification.post();
    }

    public static Response getListOfBooksIssuedToStudent(String studentId) {
        RestAssured.baseURI = "http://localhost:40080/api/v1/book/all/" + studentId;
        RequestSpecification requestSpecification =
                RestAssured.given()
                        .header(CONTENT_TYPE, APPLICATION_JSON);
        return requestSpecification.get();
    }

    public static Response getBook(String isbn) {
        RestAssured.baseURI = "http://localhost:40080/api/v1/book/" + isbn;
        RequestSpecification requestSpecification =
                RestAssured.given().header(CONTENT_TYPE, APPLICATION_JSON);
        return requestSpecification.get();
    }

}
