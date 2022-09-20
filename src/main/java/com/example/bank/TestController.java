package com.example.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/hello")
public class TestController {

    @GetMapping
    public String hello(){
        return "Hello World!";
    }
}
