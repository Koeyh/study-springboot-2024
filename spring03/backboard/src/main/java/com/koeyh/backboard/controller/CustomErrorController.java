package com.koeyh.backboard.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class CustomErrorController implements ErrorController{

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);  // 404, 500, 403등 ERROR STATUS CODE, 성공 코드는 200
        
        if(status != null) { // 에러가 발생하면
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {    // 404에러 발생 시
                return "error/404";
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500"; // 개발자에게 가장 중요한 에러
            }
        }

        return "error/error";   // 정의해 둔 status 이외의 에러 발생 시
    }
    
}
