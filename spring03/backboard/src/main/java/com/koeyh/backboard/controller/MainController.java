package com.koeyh.backboard.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Log4j2
public class MainController {

    @GetMapping("/hello")
    public String getHello() {
        log.info("getHello(); 실행.");
        return "hello";
    }
    
    @GetMapping("/")
    public String getMain() {
        return "redirect:/board/list";      // localhost:8080 으로 접속하면 localhost:8080/board/list로 변경
    }
    
}
