package org.bearfly.ewords.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class ControllerTest {

    @RequestMapping("/index")
    public String index(){
        return "demo";
    }
}