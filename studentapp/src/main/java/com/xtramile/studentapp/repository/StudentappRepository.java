package com.xtramile.studentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xtramile.studentapp.model.Student;

@Repository
public interface StudentappRepository extends JpaRepository<Student, Long> {}