package com.service.dynamic_view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.service.dynamic_view.retrofit.retrofitService;
import com.service.dynamic_view.retrofit.testAPI;
import com.service.dynamic_view.studentLayouts.testStudent;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExampleUnitTest {

    //Simpel sum test
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//        System.out.println("Test 1");
//    }



//    @Test
//    public void testApiConnectivity() throws InterruptedException {
//        System.out.println("API Connectivity Test");
//
//        retrofitService retrofitService = new retrofitService();
//        studentAPI studentAPI = retrofitService.getRetrofit().create(studentAPI.class);
//
//        // Use CountDownLatch to wait for the asynchronous call to complete
//        CountDownLatch latch = new CountDownLatch(1);
//
//        // Make the API request
//        studentAPI.getAllStudents().enqueue(new Callback<List<Student>>() {
//            @Override
//            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
//
//                if (response.isSuccessful()) {
//                    // Process the successful response
//                    List<Student> students = response.body();
//                    assertNotNull("Response body should not be null", students);
//                    assertFalse("List of students should not be empty", students.isEmpty());
//
//                    // Add more specific assertions based on your API response structure
//                    // For example, check properties of the first student in the list
//                    Student firstStudent = students.get(0);
//                    assertNotNull("First student should not be null", firstStudent);
//                    assertNotNull("Enrollment number should not be null", firstStudent.getEnrollmentNumber());
//                    // ... (add more assertions as needed)
//                } else {
//                    // Handle unsuccessful response (check status code)
//                    int statusCode = response.code();
//                    fail("Unsuccessful Response. Status Code: " + statusCode);
//                }
//                latch.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<List<Student>> call, Throwable t) {
//                // Handle failure
//                fail("Request failed: " + t.getMessage());
//
//                // Signal that the response has been received (even in case of failure)
//                latch.countDown();
//            }
//        });
//
//        // Wait for the response or timeout after a certain period (e.g., 10 seconds)
//        System.out.println("Waiting for API response");
//        boolean success = latch.await(10, TimeUnit.SECONDS);
//
//        if (success) {
//            System.out.println("API response received");
//        } else {
//            fail("Timeout waiting for API response");
//        }
//    }

//    @Test
//    public void testApiConnectivity() throws InterruptedException {
//        System.out.println("API Connectivity Test");
//
//        retrofitService retrofitService = new retrofitService();
//        studentAPI studentAPI = retrofitService.getRetrofit().create(studentAPI.class);
//
//        // Use CountDownLatch to wait for the asynchronous call to complete
//        CountDownLatch latch = new CountDownLatch(1);
//        final Student[] studentTest = {new Student()};
//
//        // Variables to store the result
//        final boolean[] success = {false};
//        final String[] errorMessage = {null};
//        final String[] sname = {""};
//        // Make the API request
//        studentAPI.getAllStudents().enqueue(new Callback<List<Student>>() {
//            @Override
//            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
//
//                if (response.isSuccessful()) {
//                    // Process the successful response
//                    List<Student> students = response.body();
//                    assertNotNull("Response body should not be null", students);
//                    assertFalse("List of students should not be empty", students.isEmpty());
//
//                    // Add more specific assertions based on your API response structure
//                    // For example, check properties of the first student in the list
//                    Student firstStudent = students.get(3);
//                    assertNotNull("First student should not be null", firstStudent);
//                    assertNotNull("Enrollment number should not be null", firstStudent.getEnrollmentNumber());
//                    // ... (add more assertions as needed)
//
//                    // Set the success flag
//                    success[0] = true;
//                    if(success[0]){
//                        sname[0] = firstStudent.getName();
//                        studentTest[0] =firstStudent;
//                    }
//                } else {
//                    // Handle unsuccessful response (check status code)
//                    int statusCode = response.code();
//                    errorMessage[0] = "Unsuccessful Response. Status Code: " + statusCode;
//                }
//                latch.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<List<Student>> call, Throwable t) {
//                // Handle failure
//                errorMessage[0] = "Request failed: " + t.getMessage();
//
//                // Signal that the response has been received (even in case of failure)
//                latch.countDown();
//            }
//        });
//
//        // Wait for the response or timeout after a certain period (e.g., 10 seconds)
//        System.out.println("Waiting for API response");
//        latch.await(10, TimeUnit.SECONDS);
//
//
//        // Check the result
//        assertTrue("API call should be successful", success[0]);
//        System.out.println("API response received "+success[0]);
//        assertNull("Error message should be null on success", errorMessage[0]);
//        System.out.println("API response received "+errorMessage[0]);
//        assertNotNull("Student name should not be null", sname[0]);
//
//        //All detail of student
//        System.out.println("Student Name: "+studentTest[0].getName());
//        System.out.println("Student Enrollment Number: "+studentTest[0].getEnrollmentNumber());
//        System.out.println("Student Branch: "+studentTest[0].getBranch());
//    }

//    @Test
//    public void addStudent() throws InterruptedException {
//        retrofitService retrofitService = new retrofitService();
//        studentAPI studentAPI = retrofitService.getRetrofit().create(studentAPI.class);
//        CountDownLatch latch = new CountDownLatch(1);
//
//        System.out.println("Adding Student");
//        Student student = createSampleStudent(); // Create a method to generate a sample student
//
//        // Logging request details
//        System.out.println("Sending request to: " + studentAPI.save(student).request().url());
//        System.out.println("Request body: " + student);
//
//        final boolean[] success = {false};
//        final String[] errorMessage = {null};
//        final Student[] testStudent = {new Student()};
//        studentAPI.save(student).enqueue(new Callback<Student>() {
//            @Override
//            public void onResponse(Call<Student> call, Response<Student> response) {
//
//                if (response.isSuccessful()) {
//                    Student savedStudent = response.body();
//                    assertNotNull("Saved student should not be null", savedStudent);
//                    assertNotNull("Student ID should not be null", savedStudent.getUserId());
//                    System.out.println("Student saved successfully with ID: " + savedStudent.getUserId());
//
//                    success[0] = true;
//                    // testStudent[0] = savedStudent; // Uncomment if you need to use the saved student in further assertions
//                } else {
//                    fail("Failed to save student. Status Code: " + response.code());
//                }
//
//                latch.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<Student> call, Throwable t) {
//                System.out.println("Failed to save student: " + t.getMessage());
//                t.printStackTrace();  // Log the full stack trace for detailed information
//                latch.countDown();
//            }
//
//        });
//
//        // Timeout handling
//        boolean succ = waitForResponse(latch);
//
//        if (succ) {
//            System.out.println("API response received");
//        } else {
//            fail("Timeout waiting for API response");
//        }
//
//        // All detail of student (Uncomment if needed)
//        // System.out.println("Student Name: " + testStudent[0].getName());
//        // System.out.println("Student Enrollment Number: " + testStudent[0].getEnrollmentNumber());
//        // System.out.println("Student Branch: " + testStudent[0].getBranch());
//    }
//
//    private boolean waitForResponse(CountDownLatch latch) throws InterruptedException {
//        System.out.println("Waiting for API response");
//        return latch.await(30, TimeUnit.SECONDS);
//    }
//
//    private Student createSampleStudent() {
//        Student student = new Student();
//        student.setUserId(77L);
//        student.setEnrollmentNumber(123994L);
//        student.setName("Tina");
//        student.setBranch("Science");
//        student.setDegree("B.Tech");
//        student.setSemester(9);
//        student.setSection("Bs");
//        student.setMedium("English");
//        student.setAdmissionDate("2022-02-15");
//        student.setFatherName("John Doe");
//        student.setMotherName("Carol Doe");
//        student.setDateOfBirth("1998-05-20");
//        student.setReligion("Christian");
//        student.setCategory("OBC");
//        student.setBloodGroup("B+");
//        student.setCasteName("ABC");
//        student.setCountry("USA");
//        student.setState("California");
//        student.setDistrict("Los Angeles");
//        student.setTempAddress("Temporary Address 2");
//        student.setPermanentAddress("Permanent Address 2");
//        student.setGender("Female");
//        student.setAdharCardNumber("120411788012");
//        student.setPanCardNumber("ABDDP1234F");
//        student.setMobileNumber("98069543910");
//        student.setEmailAddress("vabua@gmail.com");
//        student.setFatherMobileNumber("8776492101");
//        student.setFatherEmailAddress("joada@example.com");
//
//        return student;
//    }


    //Get Test Student
//    @Test
//    public void getStudent() throws InterruptedException {
//        System.out.println("Get Student Test");
//
//        retrofitService retrofitService = new retrofitService();
//        testAPI testAPI = retrofitService.getRetrofit().create(testAPI.class);
//        CountDownLatch latch = new CountDownLatch(1);
//        final Student[] studentTest = {new Student()};
//        final boolean[] success = {false};
//        final String[] errorMessage = {null};
//    }



    @Test
    public void testApiConnectivity() throws InterruptedException {
        System.out.println("API Connectivity Test");

        retrofitService retrofitService = new retrofitService();
        testAPI testAPI = retrofitService.getRetrofit().create(testAPI.class);

        // Use CountDownLatch to wait for the asynchronous call to complete
        CountDownLatch latch = new CountDownLatch(1);
//        final Student[] studentTest = {new Student()};

        // Variables to store the result
        final boolean[] success = {false};
        final String[] errorMessage = {null};
        final testStudent[] stest = {new testStudent()};
        // Make the API request
        testAPI.getTestStudent().enqueue(new Callback<List<testStudent>>() {
            @Override
            public void onResponse(Call<List<testStudent>> call, Response<List<testStudent>> response) {

                if (response.isSuccessful()) {
                    // Process the successful response
                    List<testStudent> students = response.body();//List of students
                    assertNotNull("Response body should not be null", students);
                    assertFalse("List of students should not be empty", students.isEmpty());

                    //First Student
                    testStudent firstStudent = students.get(1);
                    assertNotNull("First student should not be null", firstStudent);
                    assertNotNull("Enrollment number should not be null", firstStudent.getRollNol());

//



                    success[0] = true;
                    if(success[0]){
                        stest[0] = firstStudent;
                    }
                } else {
                    int statusCode = response.code();
                    errorMessage[0] = "Unsuccessful Response. Status Code: " + statusCode;
                }
                latch.countDown();
            }

            @Override
            public void onFailure(Call<List<testStudent>> call, Throwable t) {
                // Handle failure
                errorMessage[0] = "Request failed: " + t.getMessage();

                // Signal that the response has been received (even in case of failure)
                latch.countDown();
            }
        });

        // Wait for the response or timeout after a certain period (e.g., 10 seconds)
        System.out.println("Waiting for API response");
        latch.await(10, TimeUnit.SECONDS);


        // Check the result
        assertTrue("API call should be successful", success[0]);
        System.out.println("API response received "+success[0]);
        assertNull("Error message should be null on success", errorMessage[0]);
        System.out.println("API response received "+errorMessage[0]);

        //All detail of student
        System.out.println("Student Name: "+stest[0].getName());
        System.out.println("Student Enrollment Number: "+stest[0].getRollNol());

    }

    @Test
    public void addTestStudent() throws InterruptedException {
        retrofitService retrofitService = new retrofitService();
        testAPI testAPI = retrofitService.getRetrofit().create(testAPI.class);
        CountDownLatch latch = new CountDownLatch(1);

        System.out.println("Adding Test Student");
         testStudent testStudent = createSampleTestStudent(); // Create a method to generate a sample student
        System.out.println("Student Name: "+testStudent.getName()+ " Roll Number: "+testStudent.getRollNol());
        final boolean[] success = {false};
        final String[] errorMessage = {null};
        final testStudent[] stest = {new testStudent()};
        final String[] naam = new String[1];
        int roll;
        testAPI.save(testStudent).enqueue(new Callback<testStudent>() {
            @Override
            public void onResponse(Call<testStudent> call, Response<testStudent> response) {

                if (response.isSuccessful()) {
//                    System.out.println("Kuch toh hua hai");
                    System.out.println("Response: "+response);
                    testStudent savedTestStudent = response.body();//List of students
                    naam[0] = savedTestStudent.getName();


                    assertNotNull("Saved student should not be null", savedTestStudent);
                    System.out.println("Student saved successfully with ID: " + savedTestStudent.getRollNol());

                    success[0] = true;
                    stest[0] = savedTestStudent;
                } else {
                    System.out.println("Kuch toh hua hai");
                    fail("Failed to save student. Status Code: " + response.code());


                }


                latch.countDown();


            }

            @Override
            public void onFailure(Call<testStudent> call, Throwable t) {
                System.out.println("Failed to save student: " + t.getMessage());
                t.printStackTrace();  // Log the full stack trace for detailed information
                latch.countDown();
            }

        });

        //Student Name
        System.out.println("Waiting for API response");
        System.out.println("Student Name: "+stest[0].getName());
        System.out.println("Student Enrollment Number: "+stest[0].getRollNol());
        System.out.println("Student Name: "+naam[0]);




    }

    private testStudent createSampleTestStudent() {
        testStudent testStudent = new testStudent();
        testStudent.setName("Tina");
        testStudent.setRollNol((int)123994);
        return testStudent;
    }


}

