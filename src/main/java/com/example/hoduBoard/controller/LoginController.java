package com.example.hoduBoard.controller;

import com.example.hoduBoard.service.UserService;
import com.example.hoduBoard.domain.repository.UserRepository;
import com.example.hoduBoard.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@Controller
public class LoginController {

    UserRepository userRepository;
    private final UserService userService;

    @RequestMapping(value = {"/", "/login.do"})
    public String login(Model model, @RequestParam(value = "error", required = false)String error, @RequestParam(value = "exception", required = false)String exception){
        //model.addAttribute("list", list);
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login";
    }


    @RequestMapping("/signup.do")
    public String createUser(@ModelAttribute UserDTO user, HttpServletResponse response) throws IOException {
        Long userNo = userService.createUser(user);

        if(userNo == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('입력한 휴대폰 번호로 가입한 유저가 존재 / 로그인 요망'); location.href='/login.do';</script>");
            out.flush();
        }else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원가입 성공!'); location.href='/login.do';</script>");
            out.flush();
        }
        return "login";

    }


    @RequestMapping("/main.do")
    public String main(Model model) throws IOException {
        //model.addAttribute("list", list);
        return "main";
    }

}
