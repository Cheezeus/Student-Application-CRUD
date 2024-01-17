package com.xtramile.studentapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xtramile.studentapp.model.Student;
import com.xtramile.studentapp.service.StudentappService;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/studentapp") 
public class StudentappController { 
    // Variable
    private final StudentappService studentService;
    
    // Constructor
    public StudentappController(StudentappService studentService) { 
        this.studentService = studentService;
    } 

    // Add Student
    @PostMapping("/student") 
    public ResponseEntity<Student> addStudent(@RequestBody Student student) { 
        Student newStudent = studentService.getStudentRepository().save(student); 
        return ResponseEntity.ok(newStudent); 
    }

    // Get All Student
    @GetMapping("/student-list")  
    public ResponseEntity<List<Student>> getAllStudents() { 
        return studentService.getAllStudents(); 
    }

    // Get Student by NIM 
    @GetMapping("/student/{nim}") 
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long nim) 
    { 
        Optional<Student> student = studentService.getStudentRepository().findById(nim); 
        if (student.isPresent()) { 
            return ResponseEntity.ok(student); 
        } 
        else { 
            return ResponseEntity.notFound().build(); 
        } 
    }

    // Update Student
    @PutMapping(path = "/student/{nim}") 
    public ResponseEntity<Student> updateProduct(@PathVariable(value = "nim") Long nim, @RequestBody Student student) { 
        return studentService.updateStudent(nim, student); 
    }

    // Delete Student
    @DeleteMapping(value = "/student/{nim}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long nim) {
        studentService.deleteStudent(nim);
        return ResponseEntity.ok("Student Deleted Successfully against id " + nim);
    }

}
