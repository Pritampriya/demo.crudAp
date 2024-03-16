package com.CrudApplication.controller;

import com.CrudApplication.model.Student;
import com.CrudApplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;


    //post
    @PostMapping("/save")
    public ResponseEntity<Object> saveStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.OK).body("student saved successful");
    }

    //Get
    @GetMapping("/getStudent")
    public ResponseEntity<Object> getStudent() {
        Optional<List<Student>> studentList = Optional.of(studentRepository.findAll());

        if (studentList.isPresent() && studentList.get().size() > 0) {
            return ResponseEntity.status(HttpStatus.FOUND).body(studentList.get());

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");

        }

    }

    //put
    @PutMapping("/update/{Id}")
    //public  String updateStudentById(@PathVariable Integer id,@RequestBody Student student){
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable Integer Id) {

        Optional<Student> stud = studentRepository.findById(Id);
        if (stud.isPresent()) {
            student.setId(stud.get().getId());
            studentRepository.save(student);
            return ResponseEntity.status(HttpStatus.OK).body("Student Record updated successful" + Id);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Record not Updated" + Id);

        }
    }


    //delete

    @DeleteMapping("/DeleteAll")
    public ResponseEntity<Object> deleteAllStudent() {
        studentRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Student Record deleted successful");

    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer Id) {
        Optional<Student> stud = studentRepository.findById(Id);
        if (stud.isPresent()) {
            studentRepository.deleteById(Id);
            return ResponseEntity.status(HttpStatus.OK).body("Student Record deleted successful" + Id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Student Record  not deleted successful" + Id);
        }
    }
}







