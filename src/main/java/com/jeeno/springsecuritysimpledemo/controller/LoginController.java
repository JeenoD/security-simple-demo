package com.jeeno.springsecuritysimpledemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 杜家浩
 * @version 1.0.0
 * @date 2020/1/8 17:07
 */
@RestController
public class LoginController {

    @GetMapping("/loginPage")
    public ModelAndView loginPage(){
        return new ModelAndView("/loginPage");
    }
}
