package com.test.consumer.controller;


import com.test.consumer.controller.dto.Student;
import com.test.consumer.services.StudentConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class StudentConsumerController {

    StudentConsumerService studentConsumerService;

    @GetMapping("keepAlive")
    public ResponseEntity<String> keepAlive() {
        return ResponseEntity.ok("Keep_alive");
    }

    @GetMapping("student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") String rollId) {

        return studentConsumerService.getStudent(rollId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("students")
    public ResponseEntity<Set<Student>> getStudent() {

        Set<Student> studentSet = studentConsumerService.getStudents();

        return studentSet != null && !studentSet.isEmpty() ? ResponseEntity.ok(studentSet) : ResponseEntity.notFound().build();

    }
}
