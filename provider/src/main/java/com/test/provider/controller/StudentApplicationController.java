package com.test.provider.controller;

import com.test.provider.dto.Student;
import com.test.provider.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class StudentApplicationController {

    StudentService studentService;

    @GetMapping("keepAlive")
    public ResponseEntity<String> keepAlive() {
        return ResponseEntity.ok("Keep_alive");
    }

    @GetMapping("student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") String rollId) {

        return studentService.getStudent(rollId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("students")
    public ResponseEntity<Set<Student>> getStudent() {

        Set<Student> studentSet = studentService.getStudents();

        return studentSet != null && !studentSet.isEmpty() ? ResponseEntity.ok(studentService.getStudents()) : ResponseEntity.notFound().build();

    }
}
