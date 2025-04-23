package com.example.student_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student_api.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
