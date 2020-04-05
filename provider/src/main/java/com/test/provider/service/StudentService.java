package com.test.provider.service;

import com.test.provider.dto.Address;
import com.test.provider.dto.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    private Map<String, Student> studentDatabase;

    private StudentService() {
        studentDatabase = new HashMap<>();
        studentDatabase.put("A123", new Student("A123", "Tom Hanks", 12, new Address("Beverly Hills", "Fisherman Wharf", "KL897", "Amsterdam")));
        studentDatabase.put("A561", new Student("A561", "Joe Zolt", 22, new Address("Castle Down", "Fisherman Wharf", "BG554", "Amsterdam")));
        studentDatabase.put("A990", new Student("A990", "Tim Paul", 13, new Address("Beverly Hills", "Fisherman Wharf", "HH555", "Amsterdam")));
        studentDatabase.put("A886", new Student("A886", "Henry Rolf", 12, new Address("Beverly Hills", "Fisherman Wharf", "AF777", "Amsterdam")));
        studentDatabase.put("B110", new Student("B110", "Jenny Till", 22, new Address("Beverly Hills", "Sportlaan", "AF456", "Amsterdam")));
        studentDatabase.put("B551", new Student("B551", "Dean Saw", 17, new Address("Beverly Hills", "Fisherman Wharf", "XC453", "Amsterdam")));
        studentDatabase.put("S111", new Student("S111", "Mickey Han", 19, new Address("Town Drive", "UIthoorn Wharf", "ZX789", "Amsterdam")));
        studentDatabase.put("I899", new Student("I899", "Rose Shelly", 12, new Address("Beverly Hills", "Fisherman Wharf", "LL098", "Amsterdam")));

    }

    public Optional<Student> getStudent(String rollId) {
        return Optional.ofNullable(studentDatabase.getOrDefault(rollId, null));
    }

    public Set<Student> getStudents() {
        return new HashSet<>(studentDatabase.values());
    }

    public Student createStudent(Student data) {
        return studentDatabase.putIfAbsent(data.getRollId(), data);
    }


}
