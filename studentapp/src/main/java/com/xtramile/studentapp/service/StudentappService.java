package com.xtramile.studentapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.xtramile.studentapp.model.Student;
import com.xtramile.studentapp.repository.StudentappRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentappService {
    // Variable 
    private final StudentappRepository studentRepository; 
    
    // Constructor
    public StudentappService(StudentappRepository studentRepository) { 
        this.studentRepository = studentRepository; 
    } 

    // Add Student
    public ResponseEntity<Student> addStudent(@RequestBody Student student) { 
        Student newStudent = studentRepository.save(student); 
        return ResponseEntity.ok(newStudent); 
    }

    // Get All Student
    public ResponseEntity<List<Student>> getAllStudents() { 
        return ResponseEntity.ok(studentRepository.findAll()); 
    }

    // Get Student by NIM
    public ResponseEntity<Optional<Student>> getStudentById(Long nim) { 
        Optional<Student> student = studentRepository.findById(nim); 
        if (student.isPresent()) { 
            return ResponseEntity.ok(student); 
        } 
        else { 
            return ResponseEntity.notFound().build(); 
        } 
    }

    // Update Student
    public ResponseEntity<Student> updateStudent(Long nim, Student updatedStudent) { 
        if (nim == null) { 
            throw new IllegalArgumentException("NIM cannot be null"); 
        } 
        Student existingStudent 
        = studentRepository.findById(nim).orElseThrow( 
            () -> new EntityNotFoundException(String.valueOf(nim))); 
        existingStudent.setNamaDepan(updatedStudent.getNamaDepan()); 
        existingStudent.setNamaBelakang(updatedStudent.getNamaBelakang());
        existingStudent.setTanggalLahir(updatedStudent.getTanggalLahir());
        existingStudent.setJenisKelamin(updatedStudent.getJenisKelamin());
        Student savedEntity = studentRepository.save(existingStudent); 
        return ResponseEntity.ok(savedEntity); 
    }
    
    // Delete Student
    public ResponseEntity<String> deleteStudent(Long nim) { 
        studentRepository.deleteById(nim); 
        return ResponseEntity.ok("Student Deleted Successfully"); 
    }

    public StudentappRepository getStudentRepository() {
        return this.studentRepository;
    }
}