package com.servicec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/c")
public class SalaryController {

    @GetMapping("/salary")
    public String getSalaryDetails() {
        return "Salary details - confidential";
    }
}
