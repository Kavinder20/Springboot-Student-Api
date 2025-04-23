package com.example.student_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_api.model.Student;
import com.example.student_api.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

	private  StudentRepository studentRepository ;
	
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository ;
	}
	
	
	@GetMapping("/ping")
	public String hello() {
		return "Student API is working" ;
	}
	
	@GetMapping
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@PostMapping
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Student not found with id " + id));
	}
	
	
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
	    return studentRepository.findById(id)
	            .map(student -> {
	                student.setName(updatedStudent.getName());
	                student.setEmail(updatedStudent.getEmail());
	                student.setCourse(updatedStudent.getCourse());
	                return studentRepository.save(student);
	            })
	            .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
	}
	
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {
	    if (studentRepository.existsById(id)) {
	        studentRepository.deleteById(id);
	        return "Student with ID " + id + " has been deleted.";
	    } else {
	        throw new RuntimeException("Student not found with id " + id);
	    }
	}


}
