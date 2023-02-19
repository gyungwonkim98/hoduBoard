package com.example.hoduBoard.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String login(Model model){

        //model.addAttribute("list", list);

        return "login";
    }

}
