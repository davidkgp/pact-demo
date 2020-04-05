package com.test.consumer.services;

import com.test.consumer.controller.dto.Student;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentConsumerService {
    public Optional<Student> getStudent(String rollId) {
        return Optional.empty();
    }

    public Set<Student> getStudents() {
        return Collections.emptySet();
    }
}
