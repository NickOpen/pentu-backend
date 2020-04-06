package com.pentu.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Oriency on 2020-01-15.
 */
@Controller
@RequestMapping
public class IndexController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("hello", "欢迎进入系统");
        return "index";
    }

}
