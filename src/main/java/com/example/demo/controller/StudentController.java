package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
    
	@Autowired
    private StudentService studentService;
    
	@PostMapping("/students")
    public ResponseEntity<Void> createStudent() {
    
		List<Student>students  =  studentService.createStudent();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/{id}").buildAndExpand(students.get(0).getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
	@GetMapping("/students/{studentId}")
    public Student retrieveStudent(@PathVariable Integer studentId) {
        return studentService.retrieveStudent(studentId);
    }
}