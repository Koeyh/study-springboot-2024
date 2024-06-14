package com.koeyh.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        log.info("Spring Boot Index!");
        return "index";
    }
    
}
