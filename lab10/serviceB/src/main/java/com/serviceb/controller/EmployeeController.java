package com.serviceb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/b")
public class EmployeeController {

    @GetMapping("/employee-contact")
    public String getEmployeeContactDirectory() {
        return "Employee contact directory (phone number)";
    }
}
