package com.servicea.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/a")
public class ProductController {

    private final RestTemplate serviceClient;

    public ProductController(RestTemplate serviceClient) {
        this.serviceClient = serviceClient;
    }

    @GetMapping("/product")
    @PreAuthorize("hasAnyRole('CUSTOMER','EMPLOYEE','MANAGER')")
    public String getProductCatalog() {
        return "Product catalog - accessible to all roles";
    }

    @GetMapping("/employee-contact")
    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
    public String getEmployeeContactDirectory() {
        return serviceClient.getForObject("http://localhost:8082/api/b/employee-contact", String.class);
    }

    @GetMapping("/salary")
    @PreAuthorize("hasRole('MANAGER')")
    public String getSalaryReport() {
        return serviceClient.getForObject("http://localhost:8083/api/c/salary", String.class);
    }
}
