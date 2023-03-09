package com.manitaggarwal.exercise.controller;

import com.manitaggarwal.exercise.controller.request.IssueBookRequest;
import com.manitaggarwal.exercise.controller.response.IssueBookResponse;
import com.manitaggarwal.exercise.entiry.Book;
import com.manitaggarwal.exercise.entiry.Student;
import com.manitaggarwal.exercise.utils.APICall;
import com.manitaggarwal.exercise.utils.BaseTest;
import com.manitaggarwal.exercise.utils.DefaultData;
import com.manitaggarwal.exercise.utils.JsonUtils;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.manitaggarwal.exercise.utils.JsonUtils.getObjectFromJson;
import static com.manitaggarwal.exercise.utils.JsonUtils.getRandomNumber;

@RunWith(SpringRunner.class)
public class BookControllerFunctionalTests extends BaseTest {

    /*
     * Given: Where a book exists in the system
     * When: Call is placed to retrieve the book using isbn10
     * Then: Details of the book are fetched correctly
     * */

    @Test
    public void getBookWhenExists_ByISBN10() {

        // prerequisites
        String bookName = "Alice's Adventures in the Wonderland";
        String ibsn10 = "9789380816715";
        Book response = (Book) getObjectFromJson(
                APICall.addBook(DefaultData.getBookRequest(bookName, ibsn10)).asString(),
                Book.class);
        Assert.assertEquals("post assert - this will pass", ibsn10, response.getBookId());

        // when
        Response fetchedBookResponse = APICall.getBook(ibsn10);
        Book fetchedBook =
                (Book) getObjectFromJson(fetchedBookResponse.getBody().asString(), Book.class);
        System.out.println("fetchedBook.getBookName() = " + fetchedBook.getBookName());

        // then
        Assert.assertEquals(200, fetchedBookResponse.getStatusCode());
        Assert.assertEquals(bookName, fetchedBook.getBookName());


    }


    /*
     * Given: Where a book does not exist in the system
     * When: Call is placed to retrieve the book using ibsn10
     * Then: Exception is thrown
     * */

    @Test
    public void getBookWhenNotExists_ByISBN10() {
        // given is nothing

        // when
        Response fetchedBookResponse = APICall.getBook("092019029301");

        // then
        Assert.assertEquals(500, fetchedBookResponse.getStatusCode());

    }

    /*
     * Given: Where a book and student exist in the system
     * When: Call is placed to issue a book
     * Then: Book is issued successfully
     * */

    @Test
    public void bookAndStudentExistsIssuancePasses() {

        // given
        // create student
        String msisdn = getRandomNumber();
        String email = "valid@admin.com";
        String name = "Harry";
        Student studentSaveResponse = (Student) JsonUtils.getObjectFromJson(
                APICall.addStudent(DefaultData.getAdminRequest(msisdn, email, name)).asString(),
                Student.class);
        Assert.assertEquals("post assert - this will pass", msisdn, studentSaveResponse.getMsisdn());

        // create book
        String bookName = "Alice's Adventures in the Wonderland";
        String ibsn10 = "9789380816715";
        Book bookSaveresponse = (Book) getObjectFromJson(
                APICall.addBook(DefaultData.getBookRequest(bookName, ibsn10)).asString(),
                Book.class);
        Assert.assertEquals("post assert - this will pass", ibsn10, bookSaveresponse.getBookId());

        // when
        // issue book to a student
        IssueBookRequest request = new IssueBookRequest(studentSaveResponse.getStudentId(),
                ibsn10);

        Response bookIssueResponseRA = APICall.issueBookToStudent(request);
        Assert.assertEquals(200, bookIssueResponseRA.getStatusCode());

        IssueBookResponse issueBookResponse = (IssueBookResponse) getObjectFromJson(
                bookIssueResponseRA.getBody().asString(), IssueBookResponse.class);

        Assert.assertEquals(bookName, issueBookResponse.bookName());

        // fetch instance of an issue to check claim

        Response fetingBooksForStudent = APICall.getListOfBooksIssuedToStudent(studentSaveResponse.getStudentId());

        // then
        Assert.assertEquals("last assert", 200, fetingBooksForStudent.getStatusCode());

    }




    /*
     * Given: Where a book and student exist in the system
     * When: Call is placed to issue a book to two students
     * Then: Book issued to the first student but fails for the second
     *
     * */

    @Test
    public void bookAndStudentExistsIssuanceTo2StudentsFail() {

        // create student 1
        String msisdn = getRandomNumber();
        String email = "harry@admin.com";
        String name = "Harry";
        Student studentSaveResponse = (Student) JsonUtils.getObjectFromJson(
                APICall.addStudent(DefaultData.getAdminRequest(msisdn, email, name)).asString(),
                Student.class);
        Assert.assertEquals("post assert - this will pass", msisdn, studentSaveResponse.getMsisdn());

        // create student 2
        String student2Msisdn = getRandomNumber();
        String student2email = "ron@admin.com";
        String student2name = "Ron";
        Student student2SaveResponse = (Student) JsonUtils.getObjectFromJson(
                APICall.addStudent(DefaultData.getAdminRequest(student2Msisdn, student2email, student2name))
                        .asString(),
                Student.class);
        Assert.assertEquals("post assert - this will pass", student2Msisdn, student2SaveResponse.getMsisdn());

        // create book
        String bookName = "Don Quixote";
        String ibsn10 = "8175994436";
        Book bookSaveresponse = (Book) getObjectFromJson(
                APICall.addBook(DefaultData.getBookRequest(bookName, ibsn10)).asString(),
                Book.class);
        Assert.assertEquals("post assert - this will pass", ibsn10, bookSaveresponse.getBookId());

        // issue book to a student 1
        IssueBookRequest requestForStudent1 = new IssueBookRequest(studentSaveResponse.getStudentId(),
                ibsn10);

        Response bookIssueResponseRA = APICall.issueBookToStudent(requestForStudent1);
        // this should be successful
        Assert.assertEquals(200, bookIssueResponseRA.getStatusCode());

        IssueBookResponse issueBookResponse = (IssueBookResponse) getObjectFromJson(
                bookIssueResponseRA.getBody().asString(), IssueBookResponse.class);
        Assert.assertEquals(bookName, issueBookResponse.bookName());

        // issue book to a student 2
        IssueBookRequest requestForStudent2 = new IssueBookRequest(studentSaveResponse.getStudentId(),
                ibsn10);

        Response bookIssueResponseRAStudent2 = APICall.issueBookToStudent(requestForStudent2);
        // this should fail
        Assert.assertEquals(500, bookIssueResponseRAStudent2.getStatusCode());

    }


}
