package com.example.hoduBoard.controller;

import com.example.hoduBoard.model.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@Controller
public class LoginController {

    @RequestMapping("/")
    public String login(Model model){
        //model.addAttribute("list", list);
        return "login";
    }
//
//
//    @RequestMapping("/loginCheck.do")
//    public String login(Model model, @ModelAttribute UserDTO user, HttpServletResponse response) throws IOException {
//
//        com.example.hoduBoard.entity.user.User userEntity = userRepository.findByUserId(user.getUserId());
//
//        if(userEntity.getUserPwd().equals( user.getUserPwd())){
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('로그인 성공!'); location.href='/main.do';</script>");
//            out.flush();
//            return "main";
//        }else{
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('로그인 실패!'); location.href='/';</script>");
//            out.flush();
//            return "/";
//        }
//    }

    @RequestMapping("/main.do")
    public String main(Model model) throws IOException {
        //model.addAttribute("list", list);
        return "main";
    }

}
