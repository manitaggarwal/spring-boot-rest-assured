package com.manitaggarwal.exercise.controller;

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

import java.util.UUID;

import static com.manitaggarwal.exercise.utils.JsonUtils.getRandomNumber;

@RunWith(SpringRunner.class)
public class StudentControllerFunctionalTests extends BaseTest {

    /*
     * Given: Where a student exists in the system
     * When: Call is placed to retrieve the student using student id
     * Then: Details of the student are fetched correctly
     * */

    @Test
    public void getStudentWhenExists_ByStudentId() {

        // given - prerequisites
        String msisdn = getRandomNumber();
        String email = "harry@admin.com";
        String name = "Harry";
        Student response = (Student) JsonUtils.getObjectFromJson(
                APICall.addStudent(DefaultData.getAdminRequest(msisdn, email, name)).asString(),
                Student.class);
        Assert.assertEquals("add assert", msisdn, response.getMsisdn());

        String studentId = response.getStudentId();

        // when
        Student studentFromDatabase = (Student) JsonUtils.getObjectFromJson(APICall.getStudent(studentId).asString(),
                Student.class);

        // then
        Assert.assertEquals("get assert", studentFromDatabase.getStudentId(), studentId);

    }


    /*
     * Given: Where a student does not exist in the system
     * When: Call is placed to retrieve the student using student id
     * Then: Exception is thrown
     * */

    @Test
    public void getStudentWhenNotExists_ByStudentId() {

        // given
        String studentId = UUID.randomUUID().toString();

        // when
        Response response = APICall.getStudent(studentId);

        // then
        Assert.assertEquals("get assert", 500, response.getStatusCode());

    }


}
