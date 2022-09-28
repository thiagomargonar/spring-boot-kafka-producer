package com.estudos.kafka.springbootkafka.producer.service;


import com.estudos.kafka.springbootkafka.producer.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


@Service
public class EmployeeService {

    @Autowired
    private KafkaTemplate<Integer, Object> kafkaTemplate;

    public void publishEmployee(Employee employee) {
        try {
            var send = kafkaTemplate.send("create-employee-events-e", employee).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }


}

