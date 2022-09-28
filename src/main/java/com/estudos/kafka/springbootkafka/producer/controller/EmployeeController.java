package com.estudos.kafka.springbootkafka.producer.controller;


import com.estudos.kafka.springbootkafka.producer.dto.Employee;
import com.estudos.kafka.springbootkafka.producer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @PostMapping
    public void publishEmployee(@RequestBody Employee employee) {
        employeeService.publishEmployee(employee);
    }

}

