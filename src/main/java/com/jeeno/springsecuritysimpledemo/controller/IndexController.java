package com.jeeno.springsecuritysimpledemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 杜家浩
 * @version 1.0.0
 * @date 2020/1/8 17:07
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
