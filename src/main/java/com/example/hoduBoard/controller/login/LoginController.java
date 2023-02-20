package com.example.hoduBoard.controller.login;

import com.example.hoduBoard.model.user.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String login(Model model){

        //model.addAttribute("list", list);

        return "login";
    }


    @RequestMapping("/loginCheck.do")
    public String login(Model model, @ModelAttribute UserDTO user, HttpServletResponse response) throws IOException {

        // 나중에 JPA로 DB 접근할 수 있도록 변경 예정(현재는 하드코딩)
        if (user.getUserId().equals("test") && user.getUserPwd().equals("test123")){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 성공!'); location.href='/main.do';</script>");
            out.flush();
            return "main";
        }else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 실패!'); location.href='/';</script>");
            out.flush();
            return "/";
        }

    }

    @RequestMapping("/main.do")
    public String main(Model model) throws IOException {

        //model.addAttribute("list", list);
        return "main";
    }

}
