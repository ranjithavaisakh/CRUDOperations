package com.reskilling.CRUDOperations.controller;

import com.reskilling.CRUDOperations.model.Student;
import com.reskilling.CRUDOperations.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired // This means to get the bean called studentRepo , to create object
    private StudentRepo studentRepo;

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() { // ResponseEntity is a generic type, it can be used to return the response from the RESTful web services with a list of students
       try {
           List<Student> studentList = new ArrayList<>(); // Create a list of students
           studentRepo.findAll().forEach(studentList::add); // Fetch all the students from the database and add them to the list

           if(studentList.isEmpty()) { // If the list is empty
               return new ResponseEntity<>(studentList, HttpStatus.NO_CONTENT); // Return the list of students with no content
           }

           return new ResponseEntity<>(studentList, HttpStatus.OK); // Return the list of students with OK status

       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) { // Get the student by id
        Optional<Student> studentData = studentRepo.findById(id); // Find the student by id , it could be null or it could be present
        try {
            if(studentData.isPresent()) { // If the student is present
                return new ResponseEntity<>(studentData.get(), HttpStatus.OK); // Return the student with OK status
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return the student with NOT_FOUND status

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) { // Add a student
        try {
            Student studentObj = studentRepo.save(student); // Save the student
            return new ResponseEntity<>(studentObj, HttpStatus.CREATED); // Return the student with CREATED status

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student newStudentData) { // Update the student
        Optional<Student> oldStudentData = studentRepo.findById(id); // Find the student by id
        try {
            if(oldStudentData.isPresent()) { // If the student is present
                Student updatedStudentData = oldStudentData.get(); // Get the student
                updatedStudentData.setName(newStudentData.getName()); // Set the name of the student
                updatedStudentData.setEmail(newStudentData.getEmail()); // Set the email of the student
                updatedStudentData.setPhone(newStudentData.getPhone()); // Set the phone of the student

                Student studentObj = studentRepo.save(updatedStudentData); // Save the student
                return new ResponseEntity<>(studentObj, HttpStatus.OK); // Return the student with OK status

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return the student with NOT_FOUND status
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentRepo.deleteById(id); // Delete the student by id
        return new ResponseEntity<>(HttpStatus.OK); // Return the student with OK status
    }
}
