package com.test.consumer.services;

import com.test.consumer.controller.dto.Student;
import com.test.consumer.services.connector.ProviderConnector;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentConsumerService {

    ProviderConnector providerConnector;

    public Optional<Student> getStudent(String rollId) {
        return Optional.ofNullable(providerConnector
                .serializeData(providerConnector.getData(String.format("/student/%s", rollId)).getBody(), Student.class));
    }

    public Set<Student> getStudents() {
        return providerConnector
                .serializeData(providerConnector.getData("/students").getBody(), Set.class);
    }
}
